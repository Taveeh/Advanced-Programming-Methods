package Model.Statement;

import Exceptions.FileException;
import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements IStatement {
    IExpression expression;
    public OpenReadFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value val = expression.evaluateExpression(table, state.getHeapTable());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            if (fileTable.isVariableDefined(stringValue)) {
                throw new FileException("File is already opened");
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(stringValue.getValue()));
                    fileTable.add(stringValue, br);
                } catch (IOException exception) {
                    throw new FileException("File cannot be opened " + exception.getMessage());
                }
            }
        } else {
            throw new FileException("Expression not of string type");
        }
        state.setExecutionStack(stack);
        state.setSymbolTable(table);
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new OpenReadFileStatement(expression);
    }

    @Override
    public String toString() {
        return "open(" + expression.toString() + ")";
    }
}
