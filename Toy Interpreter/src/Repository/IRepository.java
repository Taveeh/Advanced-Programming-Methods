package Repository;

import Model.ProgramState;

public interface IRepository {
    void addState(ProgramState state);
    ProgramState getCurrentProgram();
}
