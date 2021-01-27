package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Types.Type;

public class ReturnStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        state.getSymbolTableStack().pop();
        state.setSymbolTable(state.getSymbolTableStack().top());
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new ReturnStatement();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "return";
    }
}
