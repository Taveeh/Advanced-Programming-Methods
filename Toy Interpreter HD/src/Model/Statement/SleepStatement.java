package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.ArithmeticExpression;
import Model.Expression.IExpression;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.Type;
import Model.Values.IntegerValue;
import Model.Values.Value;

public class SleepStatement implements IStatement {
    IExpression expression;

    public SleepStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        Value value = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (!value.equals(new IntegerValue(0))) {
            stack.push(new SleepStatement(new ArithmeticExpression('-', expression, new ValueExpression(new IntegerValue(1)))));
        }
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new SleepStatement(expression);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type expressionType = expression.typecheck(table);
        if (expressionType.equals(new IntegerType())) {
            return table;
        }
        throw new TypeException("Sleep must have an integer argument");
    }

    @Override
    public String toString() {
        return "sleep (" + expression.toString() + ")";
    }
}
