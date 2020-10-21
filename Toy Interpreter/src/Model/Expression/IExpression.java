package Model.Expression;

import Model.ADTs.MyIDictionary;
import Model.Exceptions.InterpreterException;
import Model.Values.Value;

public interface IExpression {
    Value evaluateExpression(MyIDictionary<String, Value> table) throws InterpreterException;
}
