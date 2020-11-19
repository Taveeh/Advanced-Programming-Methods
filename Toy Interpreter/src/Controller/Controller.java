package Controller;

import Model.ADTs.*;
import Exceptions.InterpreterException;
import Model.ProgramState;
import Model.Statement.*;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Repository.IRepository;

import java.sql.Ref;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    final IRepository repository;
    Boolean displayAll;
    ExecutorService executor;

    private Map<Integer, Value> unsafeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValue, Collection<Value> heap) {
        return Stream.concat(
                heap.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.getAddress();
                }),
                symbolTableValue.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.getAddress();
                })
        ).collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(v ->v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }
    public Controller(IRepository repository) {
        this.repository = repository;
        displayAll = false;
    }

    public void setDisplayAll(Boolean displayAll) {
        this.displayAll = displayAll;
    }

//    @SuppressWarnings("UnusedReturnValue")
//    public ProgramState oneStep(ProgramState state) throws InterpreterException {
//        MyIStack<IStatement> stack = state.getExecutionStack();
//        if (stack.isEmpty()) {
//            throw new ExecutionException("Stack is empty");
//        }
//        IStatement currentStatement = stack.pop();
//        return currentStatement.execute(state);
//    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList) {
        return inProgramList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

//    public void allSteps() throws InterpreterException{
//        ProgramState programState = repository.getCurrentProgram();
//        if (displayAll) {
//            System.out.println(displayState(programState));
//        }
//        repository.logProgramStateExecution();
//        MyIHeap<Value> heap = new MyHeap<>();
//        while (!programState.getExecutionStack().isEmpty()) {
//            try {
//                programState = oneStep(programState);
//                if (displayAll) {
//                    System.out.println(displayState(programState));
//                }
//                repository.logProgramStateExecution();
//                heap.setContent(unsafeGarbageCollector(
//                        getAddressesFromSymbolTable(
//                                programState.getSymbolTable().getContent().values(),
//                                programState.getHeapTable().getContent().values()
//                        ),
//                        programState.getHeapTable().getContent()
//                ));
//                programState.setHeapTable(heap);
//            } catch (InterpreterException exception) {
//                throw new InterpreterException(exception.getMessage());
//            }
//        }
//        System.out.println(getOutput(programState));
//    }

    private void conservativeGarbageCollector(List<ProgramState> programStateList) {
        var heap = Objects.requireNonNull(programStateList.stream()
                .map(p -> getAddressesFromSymbolTable(
                        p.getSymbolTable().getContent().values(),
                        p.getHeapTable().getContent().values()))
                .map(Collection::stream)
                .reduce(Stream::concat).orElse(null)).collect(Collectors.toList());
        programStateList.forEach(programState -> {
            programState.getHeapTable().setContent(
                    unsafeGarbageCollector(
                        heap,
                        programStateList.get(0).getHeapTable().getContent()
            ));
        });



    }
    public void allSteps() throws InterpreterException {
        executor = Executors.newFixedThreadPool(2);

        List <ProgramState> programStateList = removeCompletedPrograms(repository.getProgramList());
        while (programStateList.size() > 0) {
            conservativeGarbageCollector(programStateList);
            oneStepForAllPrograms(programStateList);

            programStateList = removeCompletedPrograms(repository.getProgramList());
        }
        executor.shutdownNow();
        repository.setProgramList(programStateList);
    }
    public void oneStepForAllPrograms(List<ProgramState> states) throws InterpreterException{
        states.forEach(p -> {
            try {
                repository.logProgramStateExecution(p);
            } catch (InterpreterException exception) {
                exception.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = states.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());
        try {
            List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            states.addAll(newProgramList);
        } catch (InterruptedException e) {
            throw new InterpreterException(e.getMessage());
        }

        states.forEach(prg -> {
            try {
                repository.logProgramStateExecution(prg);
            } catch (InterpreterException exception) {
                exception.printStackTrace();
            }
        });

        repository.setProgramList(states);
    }
    public String displayState(ProgramState state) {
        return state.toString();
    }

    public void addState(ProgramState state) {
        repository.addState(state);
    }

    public String getOutput(ProgramState state) {
        return state.getOutputConsole().toString();
    }
}
