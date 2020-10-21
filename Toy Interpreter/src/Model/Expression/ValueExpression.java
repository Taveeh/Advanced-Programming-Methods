package Model.Expression;

import Model.ADTs.MyIDictionary;
import Model.Exceptions.InterpreterException;
import Model.Values.Value;

public class ValueExpression implements IExpression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table) throws InterpreterException {
        return value;
    }
}
