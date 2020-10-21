package Model.Statement;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Exceptions.DeclarationException;
import Model.Exceptions.InterpreterException;
import Model.ProgramState;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        if (table.isVariableDefined(name)) {
            throw new DeclarationException("Variable is already declared");
        } else {
            if (type.equals(new IntegerType())) {
                table.add(name, new IntegerValue());
            }else if (type.equals(new BooleanType())) {
                table.add(name, new BooleanValue());
            } else {
                throw new DeclarationException("Type does not exist");
            }
        }
        state.setSymbolTable(table);
        state.setExecutionStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
