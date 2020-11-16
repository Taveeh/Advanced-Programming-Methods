package Model.Expression;

import Model.ADTs.MyIDictionary;
import Exceptions.InterpreterException;
import Model.ADTs.MyIHeap;
import Model.Types.IntegerType;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class ArithmeticExpression implements IExpression {
    final IExpression expression1;
    final IExpression expression2;
    int operation;

    public ArithmeticExpression(char operation, IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        switch (operation) {
            case '+' -> this.operation = 1;
            case '-' -> this.operation = 2;
            case '*' -> this.operation = 3;
            case '/' -> this.operation = 4;
        }
    }

    @Override
    public String toString() {
        return switch (operation) {
            case 1 -> expression1.toString() + "+" + expression2.toString();
            case 2 -> expression1.toString() + "-" + expression2.toString();
            case 3 -> expression1.toString() + "*" + expression2.toString();
            case 4 -> expression1.toString() + '/' + expression2.toString();
            default -> "";
        };
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value1, value2;
        value1 = expression1.evaluateExpression(table, heap);
        if (value1.getType().equals(new IntegerType())) {
            value2 = expression2.evaluateExpression(table, heap);
            if (value2.getType().equals(new IntegerType())) {
                IntegerValue integerValue1 = (IntegerValue)value1;
                IntegerValue integerValue2 = (IntegerValue)value2;
                int number1, number2;
                number1 = integerValue1.getValue();
                number2 = integerValue2.getValue();
                switch (operation) {
                    case 1:
                        return new IntegerValue(number1 + number2);
                    case 2:
                        return new IntegerValue(number1 - number2);
                    case 3:
                        return new IntegerValue(number1 * number2);
                    case 4:
                        if (number2 == 0) throw new InterpreterException("Division by zero");
                        return new IntegerValue(number1 / number2);
                    default:
                        throw new InterpreterException("Invalid operation");
                }
            } else {
                throw new InterpreterException("Second operand is not an integer");
            }
        } else {
            throw new InterpreterException("First operand is not an integer");
        }
    }
}
