package Model.Statement;

import Exceptions.InterpreterException;
import Exceptions.TypeException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.Expression.RelationalExpression;
import Model.ProgramState;
import Model.Types.Type;

import java.util.Map;

public class SwitchStatement implements IStatement {
    IExpression condition;
    IExpression case1;
    IStatement statement1;
    IExpression case2;
    IStatement statement2;
    IStatement defaultStatement;

    public SwitchStatement(IExpression condition, IExpression case1, IStatement statement1, IExpression case2, IStatement statement2, IStatement defaultStatement) {
        this.condition = condition;
        this.case1 = case1;
        this.statement1 = statement1;
        this.case2 = case2;
        this.statement2 = statement2;
        this.defaultStatement = defaultStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(
                new IfStatement(
                        new RelationalExpression(
                                "==",
                                condition,
                                case1
                        ),
                        statement1,
                        new IfStatement(
                                new RelationalExpression(
                                        "=",
                                        condition,
                                        case2
                                ),
                                statement2,
                                defaultStatement
                        )
                )
        );
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new SwitchStatement(condition, case1,  statement1, case2, statement2, defaultStatement);
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
        Type case1Type = case1.typecheck(table);
        Type case2Type = case2.typecheck(table);
        if (conditionType.equals(case1Type) && conditionType.equals(case2Type)) {
            statement1.typecheck(clone(table));
            statement2.typecheck(clone(table));
            defaultStatement.typecheck(clone(table));
            return table;
        }
        throw new TypeException("Condition and cases not of same type");
    }


    @Override
    public String toString() {
        return "switch (" + condition.toString() + ")" + "( case " + case1.toString() + ": " + statement1.toString() + ") ( case " + case2.toString() + ": " + statement2.toString() + ") default: " + defaultStatement.toString() + ")";
    }
}
