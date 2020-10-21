package Model.Expression;

import Model.ADTs.MyIDictionary;
import Model.Exceptions.InterpreterException;
import Model.Values.Value;

public class VariableExpression implements IExpression {
    String id;

    @Override
    public String toString() {
        return id;
    }

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table) throws InterpreterException {
        return table.lookup(id);
    }
}
