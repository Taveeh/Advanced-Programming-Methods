package Model.Statement;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Exceptions.DeclarationException;
import Exceptions.InterpreterException;
import Model.ProgramState;
import Model.Types.*;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.StringValue;
import Model.Values.Value;

public class VariableDeclarationStatement implements IStatement {
    final String name;
    final Type type;

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
//            if (type.equals(new IntegerType())) {
//                table.add(name, new IntegerType().defaultValue());
//            } else if (type.equals(new BooleanType())) {
//                table.add(name, new BooleanType().defaultValue());
//            } else if (type.equals(new StringType())) {
//                table.add(name, new StringType().defaultValue());
//            } else if (type instanceof ReferenceType) {
//                table.add();
//            } else {
//                throw new DeclarationException("Type does not exist");
//            }
            Value defaultValue = type.defaultValue();
            table.add(name, defaultValue);
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
