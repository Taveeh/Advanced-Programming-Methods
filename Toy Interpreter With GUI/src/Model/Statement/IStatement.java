package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
    IStatement createCopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException;
}


