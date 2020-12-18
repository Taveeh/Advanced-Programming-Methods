package Model.Expression;

import Model.ADTs.MyIDictionary;
import Exceptions.InterpreterException;
import Model.ADTs.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface IExpression {
    Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException;
    Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException;

}
