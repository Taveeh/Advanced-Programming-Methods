package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Triplet;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;

import java.util.LinkedList;

public class NewToySemaphore implements IStatement {
    String id;
    IExpression expression1;
    IExpression expression2;

    public NewToySemaphore(String id, IExpression expression1, IExpression expression2) {
        this.id = id;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        Value value1 = expression1.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        Value value2 = expression2.evaluateExpression(state.getSymbolTable(), state.getHeapTable());

        if (value1.getType().equals(new IntegerType()) && value2.getType().equals(new IntegerType())) {
            if (state.getSymbolTable().isVariableDefined(id)) {
                Value variable = state.getSymbolTable().lookup(id);
                if (variable.getType().equals(new IntegerType())) {
                    int addr = state.getToySemaphoreTable().allocate(new Triplet(((IntegerValue) value1).getValue(), new LinkedList<>(),((IntegerValue) value2).getValue()));
                    state.getSymbolTable().update(id, new IntegerValue(addr));
                } else throw new InterpreterException("Variable not int");
             } else throw new InterpreterException("Variable not defined");
        } else throw new InterpreterException("Values not int");
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NewToySemaphore(id, expression1, expression2);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }

    @Override
    public String toString() {
        return "newToySemaphore (" + id + ", " + expression1.toString() + ", " + expression2.toString() + ")";
    }
}
