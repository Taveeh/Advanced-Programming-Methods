package Model.Expression;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class ReadHeapExpression implements IExpression {
    IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value = expression.evaluateExpression(table, heap);
//        System.out.println(value.toString());
        if (value instanceof ReferenceValue) {
            ReferenceValue referenceValue = (ReferenceValue)value;
            int address = referenceValue.getAddress();
            if (heap.exists(address)) {
                return heap.get(address);
            } else {
                throw new InterpreterException("Not allocated on heap");
            }
        } else {
            throw new InterpreterException("Expression not 0f reference type");
        }
    }

    @Override
    public String toString() {
        return "readHeap(" + expression.toString() + ")";
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type type = expression.typecheck(table);
        if (type instanceof ReferenceType) {
            ReferenceType referenceType = (ReferenceType) type;
            return referenceType.getInner();
        } else {
            throw new TypeException("Expression not of reference type");
        }
    }
}
