package Model.Statement;

import Exceptions.AssignmentException;
import Exceptions.FileException;
import Exceptions.InterpreterException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Types.IntegerType;
import Model.Types.StringType;
import Model.Values.IntegerValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    IExpression expression;
    String variableName;

    public ReadFileStatement(IExpression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public IStatement createCopy() {
        return new ReadFileStatement(expression, variableName);
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if (table.isVariableDefined(variableName)) {
            Value value = table.lookup(variableName);
            if (value.getType().equals(new IntegerType())) {
                Value value1 = expression.evaluateExpression(table, state.getHeapTable());

                if (value1.getType().equals(new StringType())) {
                    StringValue stringValue = (StringValue)value1;
                    BufferedReader reader = fileTable.lookup(stringValue);
                    try {
                        String line = reader.readLine();
                        IntegerValue valueForVariable;
                        if (line == null) {
                            valueForVariable = new IntegerValue();
                        } else {
                            valueForVariable = new IntegerValue(Integer.parseInt(line));
                        }
                        table.update(variableName, valueForVariable);
                    } catch (IOException exception) {
                        throw new FileException("Cannot read from file");
                    }
                } else {
                    throw new AssignmentException("Cannot read - Expression not of type string");
                }
            } else {
                throw new AssignmentException("Variable is not of type int");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }
        state.setFileTable(fileTable);
        state.setSymbolTable(table);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ", " + variableName + ")";
    }
}
