package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.List;

public class AwaitBarrierStatement implements IStatement {
    String id;

    public AwaitBarrierStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getSymbolTable().isVariableDefined(id)) {
            Value foundIndexValue = state.getSymbolTable().lookup(id);
            if (foundIndexValue.getType().equals(new IntegerType())) {
                int foundIndex = ((IntegerValue) foundIndexValue).getValue();
                if (state.getBarrierTable().exists(foundIndex)) {
                    var pair = state.getBarrierTable().get(foundIndex);
                    if (pair.getKey() > pair.getValue().size()) {
                        if (!pair.getValue().contains(state.getId())) {
                            pair.getValue().add(state.getId());
                        }
                        state.getExecutionStack().push(this);
                    }
                } else {
                    throw new InterpreterException("Not in barrier table");
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
        return new AwaitBarrierStatement(id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "barrierAwait (" + id + ")";
    }
}
