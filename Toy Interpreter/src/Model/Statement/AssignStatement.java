package Model.Statement;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Exceptions.AssignmentException;
import Model.Exceptions.InterpreterException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Values.Value;

public class AssignStatement implements IStatement{
    String id;
    IExpression expression;

    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public IExpression getExpression() {
        return expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        if (table.isVariableDefined(id)) {
            Value value = expression.evaluateExpression(table);
            if (value.getType().equals(table.lookup(id).getType())) {
                table.update(id, value);
            } else {
                throw new AssignmentException("Type of expression and type of variable do not match");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }
        state.setExecutionStack(stack);
        state.setSymbolTable(table);
        return state;
    }

    @Override
    public String toString() {
        return id + " = " + expression.toString();
    }
}
