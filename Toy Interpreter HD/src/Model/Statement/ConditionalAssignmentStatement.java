package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.BooleanType;
import Model.Types.Type;

public class ConditionalAssignmentStatement implements IStatement {
    String id;
    IExpression expression1;
    IExpression expression2;
    IExpression expression3;

    public ConditionalAssignmentStatement(String id, IExpression expression1, IExpression expression2, IExpression expression3) {
        this.id = id;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(new IfStatement(expression1, new AssignStatement(id, expression2), new AssignStatement(id, expression3)));
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new ConditionalAssignmentStatement(id, expression1, expression2, expression3);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type conditionType = expression1.typecheck(table);
        if (conditionType.equals(new BooleanType())) {
            Type variableType = table.lookup(id);
            Type then = expression2.typecheck(table);
            Type elseType = expression3.typecheck(table);
            if (variableType.equals(then) && variableType.equals(elseType)) {
                return table;
            }
            throw new TypeException("Variable and expression not of same type");
        }
        throw new TypeException("Condition not of type bool");
    }

    @Override
    public String toString() {
        return "id = " + expression1.toString() + " ? " + expression2.toString() + " : " + expression3.toString();
    }
}
