package Model.Expression;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.BooleanType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.Value;

public class NegateExpression implements IExpression {
    IExpression expression;

    public NegateExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value = expression.evaluateExpression(table, heap);
        if (value.getType().equals(new BooleanType())) {
            BooleanValue booleanValue = (BooleanValue) value;
            return new BooleanValue(!booleanValue.getValue());
        }
        throw new InterpreterException("Value not of type bool");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type expressionType = expression.typecheck(table);
        if (expressionType.equals(new BooleanType())) {
            return new BooleanType();
        } else {
            throw new TypeException("Expression not of boolean type");
        }
    }

    @Override
    public String toString() {
        return "not " + expression.toString();
    }
}
