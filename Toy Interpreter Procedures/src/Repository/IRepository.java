package Repository;

import Exceptions.InterpreterException;
import Model.ProgramState;

import java.util.List;

public interface IRepository {
    void addState(ProgramState state);
//    ProgramState getCurrentProgram();
    void logProgramStateExecution(ProgramState state) throws InterpreterException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStateList);
}
