package Model.Statement;

import Exceptions.InterpreterException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
    IStatement createCopy();
}


