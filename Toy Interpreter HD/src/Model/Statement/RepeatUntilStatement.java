package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.Expression.NegateExpression;
import Model.ProgramState;
import Model.Types.BooleanType;
import Model.Types.Type;

import java.util.Map;

public class RepeatUntilStatement implements IStatement {
    IExpression condition;
    IStatement statement;

    public RepeatUntilStatement(IExpression condition, IStatement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();

        stack.push(new CompoundStatement(
                statement,
                new WhileStatement(
                        new NegateExpression(condition),
                        statement
                )
        ));

        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new RepeatUntilStatement(condition, statement);
    }

    private static MyIDictionary<String, Type> clone(MyIDictionary<String, Type> table) throws InterpreterException {
        MyIDictionary<String, Type> newSymbolTable = new MyDictionary<>();
        for (Map.Entry<String, Type> entry: table.getContent().entrySet()) {
            newSymbolTable.add(entry.getKey(), entry.getValue());
        }
        return newSymbolTable;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type conditionType = condition.typecheck(table);
        if (conditionType.equals(new BooleanType())) {
            statement.typecheck(clone(table));
            return table;
        }
        throw new TypeException("Condition not of type bool");
    }

    @Override
    public String toString() {
        return "repeat { " + statement.toString() + " } until (" + condition.toString() + ")";
    }
}
