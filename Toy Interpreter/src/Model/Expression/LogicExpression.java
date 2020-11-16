package Model.Expression;

import Model.ADTs.MyIDictionary;
import Exceptions.InterpreterException;
import Model.ADTs.MyIHeap;
import Model.Types.BooleanType;
import Model.Values.BooleanValue;
import Model.Values.Value;

public class LogicExpression implements IExpression {
    final IExpression expression1;
    final IExpression expression2;
    final int operation;

    @Override
    public String toString() {
        return switch (operation) {
            case 1 -> expression1.toString() + "&" + expression2.toString();
            case 2 -> expression1.toString() + "|" + expression2.toString();
            default -> "";
        };
    }

    public LogicExpression(IExpression expression1, IExpression expression2, int operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value1, value2;
        value1 = expression1.evaluateExpression(table, heap);
        if (value1.getType().equals(new BooleanType())) {
            value2 = expression2.evaluateExpression(table, heap);
            if (value2.getType().equals(new BooleanType())) {
                BooleanValue booleanValue1 = (BooleanValue)value1;
                BooleanValue booleanValue2 = (BooleanValue)value2;
                Boolean bool1 = booleanValue1.getValue();
                Boolean bool2 = booleanValue2.getValue();
                return switch (operation) {
                    case 1 -> new BooleanValue(bool1 & bool2);
                    case 2 -> new BooleanValue(bool1 | bool2);
                    default -> throw new InterpreterException("Invalid operation");
                };
            } else {
                throw new InterpreterException("Second operand is not a boolean");
            }
        } else {
            throw new InterpreterException("First operand is not a boolean");
        }
    }
}
