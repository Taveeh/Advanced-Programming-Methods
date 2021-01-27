package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Types.Type;
import javafx.util.Pair;

import java.util.List;

public class NewProcedureStatement implements IStatement {
    String procedureName;
    List<String> parameterNames;
    IStatement statement;

    public NewProcedureStatement(String procedureName, List<String> parameterNames, IStatement statement) {
        this.procedureName = procedureName;
        this.parameterNames = parameterNames;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getProcedureTable().exists(procedureName)) {
            throw new InterpreterException("Procedure already exists");
        }
        state.getProcedureTable().put(procedureName, new Pair<>(parameterNames, statement));
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NewProcedureStatement(procedureName, parameterNames, statement);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "procedure " + procedureName + " (" + parameterNames.toString() + ") " + statement.toString();
    }
}
