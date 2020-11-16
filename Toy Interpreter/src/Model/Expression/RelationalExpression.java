package Model.Expression;


import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.IntegerType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class RelationalExpression implements IExpression {
    String operation;
    IExpression expression1;
    IExpression expression2;

    public RelationalExpression(String operation, IExpression expression1, IExpression expression2) {
        this.operation = operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value1 = expression1.evaluateExpression(table, heap);
        if (value1.getType().equals(new IntegerType())) {
            Value value2 = expression2.evaluateExpression(table, heap);
            if (value2.getType().equals(new IntegerType())) {
                IntegerValue integerValue1 = (IntegerValue)value1;
                IntegerValue integerValue2 = (IntegerValue)value2;
                int intValue1 = integerValue1.getValue();
                int intValue2 = integerValue2.getValue();
                return switch (operation) {
                    case "<" -> new BooleanValue(intValue1 < intValue2);
                    case "<=" -> new BooleanValue(intValue1 <= intValue2);
                    case "==" -> new BooleanValue(intValue1 == intValue2);
                    case "!=" -> new BooleanValue(intValue1 != intValue2);
                    case ">" -> new BooleanValue(intValue1 > intValue2);
                    case ">=" -> new BooleanValue(intValue1 >= intValue2);
                    default -> throw new InterpreterException("Invalid operation");
                };
            } else {
                throw new InterpreterException("Expression 2 not of integer type");
            }
        } else {
            throw new InterpreterException("Expression 1 not of integer type");
        }
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
