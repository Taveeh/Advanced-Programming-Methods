package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class AwaitStatement implements IStatement {
    String id;

    public AwaitStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {

        if (state.getSymbolTable().isVariableDefined(id)) {
            Value foundIndex = state.getSymbolTable().lookup(id);
            if (foundIndex.getType().equals(new IntegerType())) {
                int found = ((IntegerValue) foundIndex).getValue();
                if (state.getLatchTable().exists(found)) {
                    if (state.getLatchTable().get(found) != 0) {
                        state.getExecutionStack().push(this);
                    }
                } else {
                    throw new InterpreterException("Latch does not exist");
                }
            } else {
                throw new InterpreterException("Variable not of type int");
            }
        } else {
            throw new InterpreterException("Variable not defined");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new AwaitStatement(id);
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
        return "await (" + id + ")";
    }
}
