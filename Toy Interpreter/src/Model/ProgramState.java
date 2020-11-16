package Model;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIList;
import Model.Statement.IStatement;
import Model.ADTs.MyIStack;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileDescriptor;

public class ProgramState {
    MyIStack<IStatement> executionStack;
    MyIDictionary<String, Value> symbolTable;
    MyIList<Value> outputConsole;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Value> heapTable;
    IStatement originalProgram;

    public MyIHeap<Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(MyIHeap<Value> heapTable) {
        this.heapTable = heapTable;
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heapTable, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.originalProgram = originalProgram;
        if (originalProgram != null) {
            executionStack.push(originalProgram);
        }
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heapTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
    }

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

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.originalProgram = originalProgram;
        if (originalProgram != null) {
            executionStack.push(originalProgram);
        }
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
        return " --------Execution Stack-------- \n" +
                executionStack.toString() + '\n' +
                " -------- Symbol  Table -------- \n" +
                symbolTable.toString() + '\n' +
                " -------- Output Console -------- \n" +
                outputConsole.toString() + "\n" +
                " --------  File  Table  -------- \n" +
                fileTable.toString() + '\n' +
                " --------  Heap  Table  -------- \n" +
                heapTable.toString() + '\n' +
                " ------------------------------- \n\n\n";
    }
}
