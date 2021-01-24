package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.Expression.RelationalExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.Type;

import java.util.Map;

public class ForStatement implements IStatement {
    IExpression expression1;
    IExpression expression2;
    IExpression expression3;
    IStatement statement;

    public ForStatement(IExpression expression1, IExpression expression2, IExpression expression3, IStatement statement) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new AssignStatement("v", expression1),
                        new WhileStatement(
                                new RelationalExpression(
                                        "<",
                                        new VariableExpression("v"),
                                        expression2),
                                new CompoundStatement(
                                        statement,
                                        new AssignStatement("v", expression3)
                                )
                        )
                )
        ));
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new ForStatement(expression1, expression2, expression3, statement);
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
        MyIDictionary<String, Type> table1 = new VariableDeclarationStatement("v", new IntegerType()).typecheck(clone(table));
        Type vType = table1.lookup("v");
        Type expression1Type = expression1.typecheck(table1);
        Type expression2Type = expression2.typecheck(table1);
        Type expression3Type = expression3.typecheck(table1);
        if (vType.equals(new IntegerType())) {
            if (expression1Type.equals(new IntegerType())) {
                if (expression2Type.equals(new IntegerType())) {
                    if (expression3Type.equals(new IntegerType())) {
                        statement.typecheck(clone(table1));
                        return table;
                    }
                    throw new TypeException("Expression 3 not of type int");
                }
                throw new TypeException("Expression 2 not of type int");
            }
            throw new TypeException("Expression 1 not of type int");
        }
        throw new TypeException("V not of type int");
    }

    @Override
    public String toString() {
        return "for (v = " + expression1.toString() + "; v < " + expression2.toString() + "; v = " + expression3.toString() + ") { " + statement.toString() + " }";
    }
}
