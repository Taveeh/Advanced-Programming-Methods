package Repository;

import Exceptions.InterpreterException;
import Model.ProgramState;

public interface IRepository {
    void addState(ProgramState state);
    ProgramState getCurrentProgram();
    void logProgramStateExecution() throws InterpreterException;
}
