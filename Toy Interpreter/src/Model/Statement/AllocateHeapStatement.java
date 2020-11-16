package Model.Statement;

import Exceptions.AssignmentException;
import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.ReferenceType;
import Model.Values.ReferenceValue;
import Model.Values.Value;

public class AllocateHeapStatement implements IStatement{
    String variableName;
    IExpression expression;

    public AllocateHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heapTable = state.getHeapTable();

        if (symbolTable.isVariableDefined(variableName)) {
            if (symbolTable.lookup(variableName).getType() instanceof ReferenceType) {
                Value value = expression.evaluateExpression(symbolTable, heapTable);
                Value tableValue = symbolTable.lookup(variableName);
//                System.out.println(value.getType().toString());
//                System.out.println(((ReferenceType)(tableValue.getType())).getInner());
                if (value.getType().equals(((ReferenceType)(tableValue.getType())).getInner())) {
                    int addr = heapTable.allocate(value);
                    symbolTable.update(variableName, new ReferenceValue(addr, value.getType()));
                } else {
                    throw new AssignmentException("Value is not of correct type");
                }
            } else {
                throw new AssignmentException("Variable is not of reference type");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }

        state.setHeapTable(heapTable);
        state.setSymbolTable(symbolTable);
        state.setExecutionStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return "new(" + variableName + ',' + expression +
                ')';
    }
}
