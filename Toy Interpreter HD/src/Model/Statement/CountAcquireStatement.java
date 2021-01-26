package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ProgramState;
import Model.Triplet;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class CountAcquireStatement implements IStatement {
    String id;

    public CountAcquireStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getSymbolTable().isVariableDefined(id)) {
            Value foundIndexValue = state.getSymbolTable().lookup(id);
            if (foundIndexValue.getType().equals(new IntegerType())) {
                int foundIndex = ((IntegerValue) foundIndexValue).getValue();
                if (state.getCountSemaphoreTable().exists(foundIndex)) {
                    Pair<Integer, List<Integer>> pair = state.getCountSemaphoreTable().get(foundIndex);
                    if (pair.getKey() > pair.getValue().size()) {
                        if (!pair.getValue().contains(state.getId())) {
                            pair.getValue().add(state.getId());
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
        return new CountAcquireStatement(id);
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
        return "countAcquire (" + id + ")";
    }
}
