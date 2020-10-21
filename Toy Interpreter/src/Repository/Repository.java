package Repository;

import Model.ProgramState;

import java.util.LinkedList;

public class Repository implements IRepository {
    LinkedList<ProgramState> programStates;

    public Repository() {
        programStates = new LinkedList<ProgramState>();
    }

    @Override
    public void addState(ProgramState state) {
        programStates.addLast(state);
    }

    @Override
    public ProgramState getCurrentProgram() {
        ProgramState current = programStates.getFirst();
        programStates.removeFirst();
        return current;
    }
}
