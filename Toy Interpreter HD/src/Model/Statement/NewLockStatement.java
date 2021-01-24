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

public class NewLockStatement implements IStatement {
    String id;

    public NewLockStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyILockTable<Value> lockTable = state.getLockTable();

        if (symbolTable.isVariableDefined(id)) {
            Value variableValue = symbolTable.lookup(id);
            if (variableValue.getType().equals(new IntegerType())) {
                int addr = lockTable.allocate(variableValue);
                symbolTable.update(id, new IntegerValue(addr));
            } else {
                throw new InterpreterException("Variable not of type int");
            }
        } else {
            throw new InterpreterException("Variable is not defined");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NewLockStatement(id);
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
        return "newLock (" + id + ")";
    }
}
