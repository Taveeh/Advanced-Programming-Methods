package Model.Statement;

import Exceptions.ConditionException;
import Exceptions.InterpreterException;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.BooleanType;
import Model.Values.BooleanValue;
import Model.Values.Value;

public class WhileStatement implements IStatement {

    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        Value expressionValue = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (expressionValue.getType().equals(new BooleanType())) {
            if (expressionValue.equals(new BooleanValue(true))) {
                stack.push(new WhileStatement(expression, statement));
                stack.push(statement);
            }
        } else {
            throw new ConditionException("Expression not of type bool");
        }

        state.setExecutionStack(stack);
        return state;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") { " + statement.toString() + "}";
    }
}
