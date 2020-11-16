package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class WriteHeapStatement implements IStatement {
    String variableName;
    IExpression expression;

    public WriteHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heap = state.getHeapTable();

        if (symbolTable.isVariableDefined(variableName)) {
            Value value = symbolTable.lookup(variableName);
            if (value instanceof ReferenceValue) {
                int referenceAddress = ((ReferenceValue)value).getAddress();
                if (heap.exists(referenceAddress)) {
                    Value value1 = expression.evaluateExpression(symbolTable, heap);
                    if (value1.getType().equals(heap.get(referenceAddress).getType())) {
                        heap.put(referenceAddress, value1);
                    } else {
                        throw new InterpreterException("Expression not of variable type");
                    }
                } else {
                    throw new InterpreterException("Value does not exist on heap");
                }
            } else {
                throw new InterpreterException("Value is not a reference");
            }
        } else {
            throw new InterpreterException("Variable not declared");
        }
        state.setSymbolTable(symbolTable);
        state.setHeapTable(heap);
        return state;
    }

    @Override
    public String toString() {
        return "writeHeap(" + variableName + ", " + expression.toString() + ")";
    }
}
