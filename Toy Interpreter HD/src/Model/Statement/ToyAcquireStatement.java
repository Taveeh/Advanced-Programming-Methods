package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Triplet;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class ToyAcquireStatement implements IStatement {
    String id;

    public ToyAcquireStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getSymbolTable().isVariableDefined(id)) {
            Value foundIndexValue = state.getSymbolTable().lookup(id);
            if (foundIndexValue.getType().equals(new IntegerType())) {
                int foundIndex = ((IntegerValue) foundIndexValue).getValue();
                if (state.getToySemaphoreTable().exists(foundIndex)) {
                    Triplet triplet = state.getToySemaphoreTable().get(foundIndex);
                    if (triplet.first - triplet.third > triplet.second.size()) {
                        if (!triplet.second.contains(state.getId())) {
                            triplet.second.add(state.getId());
                        }
                    } else {
                        state.getExecutionStack().push(this);
                    }
                } else {
                    throw new InterpreterException("Not in sem table");
                }
            } else {
                throw new InterpreterException("Not an integer");
            }
        } else {
            throw new InterpreterException("Not declared");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new ToyAcquireStatement(id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "ToyAcquire (" + id + ")";
    }
}
