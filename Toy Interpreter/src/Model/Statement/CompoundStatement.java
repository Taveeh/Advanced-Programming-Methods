package Model.Statement;

import Model.ADTs.MyIStack;
import Exceptions.InterpreterException;
import Model.ProgramState;

public class CompoundStatement implements IStatement {
    final IStatement firstStatement;
    final IStatement secondStatement;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new CompoundStatement(firstStatement, secondStatement);
    }

    @Override
    public String toString() {
        return firstStatement.toString() + "; " + secondStatement.toString();
    }
}
