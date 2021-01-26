package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.LinkedList;

public class NewBarrierStatement implements IStatement {
    String id;
    IExpression expression;

    public NewBarrierStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        Value expressionResult = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (expressionResult.getType().equals(new IntegerType())) {
            IntegerValue num1 = (IntegerValue) expressionResult;
            if (state.getSymbolTable().isVariableDefined(id)) {
                Value variableValue = state.getSymbolTable().lookup(id);
                if (variableValue.getType().equals(new IntegerType())) {
                    int addr = state.getBarrierTable().allocate(new Pair<>(num1.getValue(), new LinkedList<>()));
                    state.getSymbolTable().update(id, new IntegerValue(addr));
                }
            } else throw new InterpreterException("Variable not defined");
        } else {
            throw new InterpreterException("Expression not of type int");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NewBarrierStatement(id, expression);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "newBarrier (" + id + ", " + expression.toString() + ")";
    }
}
