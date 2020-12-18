package Model.Expression;

import Model.ADTs.MyIDictionary;
import Exceptions.InterpreterException;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExpression implements IExpression {
    final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        return value;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return value.getType();
    }
}
