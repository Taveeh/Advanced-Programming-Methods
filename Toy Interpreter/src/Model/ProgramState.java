package Model;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIList;
import Model.Statement.IStatement;
import Model.ADTs.MyIStack;
import Model.Values.Value;

public class ProgramState {
    MyIStack<IStatement> executionStack;
    MyIDictionary<String, Value> symbolTable;
    MyIList<Value> outputConsole;
    IStatement originalProgram;

    public MyIDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(MyIDictionary<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public MyIList<Value> getOutputConsole() {
        return outputConsole;
    }

    public void setOutputConsole(MyIList<Value> outputConsole) {
        this.outputConsole = outputConsole;
    }

    public MyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }
    public void setExecutionStack(MyIStack<IStatement> newExecutionStack) {
        executionStack = newExecutionStack;
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.originalProgram = originalProgram;
        if (originalProgram != null)
            executionStack.push(originalProgram);
    }

    @Override
    public String toString() {
        return " -------- Output Console -------- \n" +
                outputConsole.toString() + "\n" +
                " -------- Symbol  Table -------- \n" +
                symbolTable.toString() + '\n' +
                " --------Execution Stack-------- \n" +
                executionStack.toString() + '\n' +
                " ------------------------------- \n\n\n";
    }
}
