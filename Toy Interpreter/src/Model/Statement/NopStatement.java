package Model.Statement;

import Model.ADTs.MyIStack;
import Model.Exceptions.InterpreterException;
import Model.ProgramState;

public class NopStatement implements IStatement{
    public NopStatement() {}

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        state.setExecutionStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return "\n";
    }
}
