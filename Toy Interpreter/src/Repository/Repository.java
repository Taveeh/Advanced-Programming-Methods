package Repository;

import Exceptions.FileException;
import Exceptions.InterpreterException;
import Model.ProgramState;

import java.io.*;
import java.util.LinkedList;

public class Repository implements IRepository {
    final LinkedList<ProgramState> programStates;
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
        programStates.addLast(state);
    }

    @Override
    public ProgramState getCurrentProgram() {
        ProgramState current = programStates.getFirst();
//        programStates.removeFirst();
        return current;
    }

    @Override
    public void logProgramStateExecution() throws InterpreterException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        }catch (IOException exception) {
            throw new FileException(exception.getMessage());
        }
        ProgramState state = programStates.getFirst();
        logFile.println(state.toString());
        logFile.flush();
        if (state.getExecutionStack().isEmpty()) {
            logFile.close();
            programStates.removeFirst();
        }
    }
}
