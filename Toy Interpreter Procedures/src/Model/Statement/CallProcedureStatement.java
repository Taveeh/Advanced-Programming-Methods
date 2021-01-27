package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CallProcedureStatement implements IStatement {
    String procedureName;
    List<IExpression> parameterValues;

    public CallProcedureStatement(String procedureName, List<IExpression> parameterValues) {
        this.procedureName = procedureName;
        this.parameterValues = parameterValues;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getProcedureTable().exists(procedureName)) {
            Pair<List<String>, IStatement> pair = state.getProcedureTable().get(procedureName);
            if (parameterValues.size() == pair.getKey().size()) {
                MyIDictionary<String, Value> procedureSymbolTable = new MyDictionary<>();
                for (int i = 0; i < parameterValues.size(); i++) {
                    Value currentValue = parameterValues.get(i).evaluateExpression(state.getSymbolTable(), state.getHeapTable());
                    procedureSymbolTable.add(pair.getKey().get(i), currentValue);
                }
                state.getExecutionStack().push(new ReturnStatement());
                state.getExecutionStack().push(pair.getValue());
                state.getSymbolTableStack().push(procedureSymbolTable);
                state.setSymbolTable(procedureSymbolTable);
            } else {
                throw new InterpreterException("Invalid number of parameters");
            }
        } else {
            throw new InterpreterException("Procedure does not exist");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new CallProcedureStatement(procedureName, parameterValues);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "call " + procedureName + " (" + parameterValues.toString() + ")";
    }
}
