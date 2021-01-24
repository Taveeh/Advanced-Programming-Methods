package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Types.Type;

public class LockStatement implements IStatement {

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        return null;
    }

    @Override
    public IStatement createCopy() {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return null;
    }


}
