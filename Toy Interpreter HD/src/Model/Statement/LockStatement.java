package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyILockTable;
import Model.ADTs.MyIStack;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class LockStatement implements IStatement {
    String id;

    public LockStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyILockTable<Integer> lockTable = state.getLockTable();

        if (symbolTable.isVariableDefined(id)) {
            Value variableValue = symbolTable.lookup(id);
            if (variableValue.getType().equals(new IntegerType())) {
                IntegerValue foundIndex = (IntegerValue) symbolTable.lookup(id);
                if (!lockTable.exists(foundIndex.getValue())) {
                    throw new InterpreterException("Lock does not exist");
                } else {
                    if (lockTable.get(foundIndex.getValue()) == -1) {
                        lockTable.update(foundIndex.getValue(), state.getId());
                    } else {
                        stack.push(this);
                    }
                }
            } else {
                throw new InterpreterException("Variable not of type int");
            }
        } else {
            throw new InterpreterException("Variable not defined");
        }
        state.setLockTable(lockTable);
        state.setSymbolTable(symbolTable);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new LockStatement(id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type variableType = table.lookup(id);
        if (variableType.equals(new IntegerType())) {
            return table;
        }
        throw new TypeException("Variable not of type int");
    }

    @Override
    public String toString() {
        return "lock (" + id + ")";
    }
}
