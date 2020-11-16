package Model.Statement;

import Model.ADTs.MyIStack;
import Exceptions.ConditionException;
import Exceptions.InterpreterException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.BooleanType;
import Model.Values.BooleanValue;
import Model.Values.Value;

public class IfStatement implements IStatement {
    final IExpression expression;
    final IStatement thenStatement;
    final IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "( IF (" + expression.toString() + ") THEN (" + thenStatement.toString() + ") ELSE (" + elseStatement.toString() + "))";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        Value condition = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (!condition.getType().equals(new BooleanType())) {
            throw new ConditionException("Condition is not of boolean type");
        }
        if (condition.equals(new BooleanValue(true))) {
            stack.push(thenStatement);
        } else {
            stack.push(elseStatement);
        }
        state.setExecutionStack(stack);
        return state;
    }

}
