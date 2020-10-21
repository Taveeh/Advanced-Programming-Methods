package Model.Statement;

import Model.Exceptions.InterpreterException;
import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
}

