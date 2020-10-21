package Model.Statement;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIList;
import Model.ADTs.MyIStack;
import Model.Exceptions.InterpreterException;
import Model.ProgramState;
import Model.Expression.IExpression;
import Model.Values.Value;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIList<Value> outConsole = state.getOutputConsole();
        outConsole.addElement(expression.evaluateExpression(state.getSymbolTable()));
        state.setExecutionStack(stack);
        state.setOutputConsole(outConsole);
        return state;
    }
}
