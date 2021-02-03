package Model;

import Exceptions.ExecutionException;
import Exceptions.InterpreterException;
import Model.ADTs.*;
import Model.Statement.IStatement;
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
    MyILatchTable<Integer> latchTable;

    public MyILatchTable<Integer> getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyILatchTable<Integer> latchTable) {
        this.latchTable = latchTable;
    }

    public static Integer lastID = 1;
    private Integer id;
    public synchronized void setId() {
        lastID++;
        id = lastID;
    }

    public Integer getId() {
        return id;
    }

    public MyIHeap<Value> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(MyIHeap<Value> heapTable) {
        this.heapTable = heapTable;
    }

    public void typeCheck() throws InterpreterException{
        originalProgram.typecheck(new MyDictionary<>());
    }
    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heapTable, MyILatchTable<Integer> latchTable, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.originalProgram = originalProgram;
        this.latchTable = latchTable;
        id = 1;
        if (originalProgram != null) {
            executionStack.push(originalProgram);
        }
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heapTable, MyILatchTable<Integer> latchTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.latchTable = latchTable;
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

    public Boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }
    public ProgramState oneStep() throws InterpreterException {
        if (executionStack.isEmpty()) {
            throw new ExecutionException("Stack is empty");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }
    @Override
    public String toString() {
        return  "ProgramID: --------- " + id.toString() + " ---------\n" +
                " --------Execution Stack-------- \n" +
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
