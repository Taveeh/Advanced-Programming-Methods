package Repository;

import Exceptions.InterpreterException;
import Model.ADTs.MyIList;
import Model.ProgramState;
import Model.Values.Value;

import java.util.List;

public interface IRepository {
    void addState(ProgramState state);
//    ProgramState getCurrentProgram();
    void logProgramStateExecution(ProgramState state) throws InterpreterException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStateList);

}
