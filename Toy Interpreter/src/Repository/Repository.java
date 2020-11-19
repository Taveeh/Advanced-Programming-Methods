package Repository;

import Exceptions.FileException;
import Exceptions.InterpreterException;
import Model.ProgramState;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Repository implements IRepository {
    List<ProgramState> programStates;
    String logFilePath;
    public Repository() {
        programStates = new LinkedList<>();
    }

    public Repository(String log) throws InterpreterException {
        try {
            PrintWriter clearFile = new PrintWriter(new BufferedWriter(new FileWriter(log)));
        } catch (IOException exception) {
            throw new InterpreterException("Log file could not be opened");
        }
        programStates = new LinkedList<>();
        logFilePath = log;
    }
    @Override
    public void addState(ProgramState state) {
        programStates.add(state);
    }

//    @Override
//    public ProgramState getCurrentProgram() {
//        ProgramState current = programStates.get(0);
////        programStates.removeFirst();
//        return current;
//    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws InterpreterException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        }catch (IOException exception) {
            throw new FileException(exception.getMessage());
        }
        logFile.println(state.toString());
        logFile.flush();
        if (state.getExecutionStack().isEmpty()) {
            logFile.close();
            programStates.remove(0);
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return programStates;
    }

    @Override
    public void setProgramList(List<ProgramState> programStateList) {
        programStates = programStateList;
    }
}
