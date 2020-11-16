package Controller;

import Model.ADTs.*;
import Exceptions.ExecutionException;
import Exceptions.InterpreterException;
import Model.ProgramState;
import Model.Statement.*;
import Model.Values.ReferenceValue;
import Model.Values.Value;
import Repository.IRepository;

import java.sql.Ref;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    final IRepository repository;
    Boolean displayAll;


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

    @SuppressWarnings("UnusedReturnValue")
    public ProgramState oneStep(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty()) {
            throw new ExecutionException("Stack is empty");
        }
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allSteps() throws InterpreterException{
        ProgramState programState = repository.getCurrentProgram();
        if (displayAll) {
            System.out.println(displayState(programState));
        }
        repository.logProgramStateExecution();
        MyIHeap<Value> heap = new MyHeap<>();
        while (!programState.getExecutionStack().isEmpty()) {
            try {
                programState = oneStep(programState);
                if (displayAll) {
                    System.out.println(displayState(programState));
                }
                repository.logProgramStateExecution();
                heap.setContent(unsafeGarbageCollector(
                        getAddressesFromSymbolTable(
                                programState.getSymbolTable().getContent().values(),
                                programState.getHeapTable().getContent().values()
                        ),
                        programState.getHeapTable().getContent()
                ));
                programState.setHeapTable(heap);
            } catch (InterpreterException exception) {
                throw new InterpreterException(exception.getMessage());
            }
        }
        System.out.println(getOutput(programState));
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
