package Model.Statement;

import Exceptions.InterpreterException;
import Model.ProgramState;

public class NopStatement implements IStatement{
    public NopStatement() {}

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
//        MyIStack<IStatement> stack = state.getExecutionStack();
//        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() {
        return "\n";
    }
}
