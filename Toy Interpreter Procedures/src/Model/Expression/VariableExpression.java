package Model.Expression;

import Model.ADTs.MyIDictionary;
import Exceptions.InterpreterException;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class VariableExpression implements IExpression {
    final String id;

    @Override
    public String toString() {
        return id;
    }

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        return table.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table.lookup(id);
    }
}
