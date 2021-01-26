package Model;

import Exceptions.ExecutionException;
import Exceptions.InterpreterException;
import Model.ADTs.*;
import Model.Statement.IStatement;
import Model.Values.StringValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.List;

public class ProgramState {
    MyIStack<IStatement> executionStack;
    MyIDictionary<String, Value> symbolTable;
    MyIList<Value> outputConsole;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Value> heapTable;
    IStatement originalProgram;
    MyILockTable<Integer> lockTable;
    MyILatchTable<Integer> latchTable;
    MyIToySemaphoreTable<Triplet> toySemaphoreTable;
    MyIBarrierTable<Pair<Integer, List<Integer>>> barrierTable;

    public MyIBarrierTable<Pair<Integer, List<Integer>>> getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable(MyIBarrierTable<Pair<Integer, List<Integer>>> barrierTable) {
        this.barrierTable = barrierTable;
    }

    MyICountSemaphore<Pair<Integer, List<Integer>>> countSemaphoreTable;

    public MyILatchTable<Integer> getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyILatchTable<Integer> latchTable) {
        this.latchTable = latchTable;
    }

    public MyIToySemaphoreTable<Triplet> getToySemaphoreTable() {
        return toySemaphoreTable;
    }

    public void setToySemaphoreTable(MyIToySemaphoreTable<Triplet> toySemaphoreTable) {
        this.toySemaphoreTable = toySemaphoreTable;
    }

    public MyICountSemaphore<Pair<Integer, List<Integer>>> getCountSemaphoreTable() {
        return countSemaphoreTable;
    }

    public void setCountSemaphoreTable(MyICountSemaphore<Pair<Integer, List<Integer>>> countSemaphoreTable) {
        this.countSemaphoreTable = countSemaphoreTable;
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


    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heapTable, MyILockTable<Integer> lockTable, MyILatchTable<Integer> latchTable, MyIToySemaphoreTable<Triplet> toySemaphoreTable, MyICountSemaphore<Pair<Integer, List<Integer>>> countSemaphoreTable, MyIBarrierTable<Pair<Integer, List<Integer>>> barrierTable, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.originalProgram = originalProgram;
        this.lockTable = lockTable;
        this.latchTable = latchTable;
        this.toySemaphoreTable = toySemaphoreTable;
        this.countSemaphoreTable = countSemaphoreTable;
        this.barrierTable = barrierTable;
        id = 1;
        if (originalProgram != null) {
            executionStack.push(originalProgram);
        }
    }

    public ProgramState(MyIStack<IStatement> executionStack, MyIDictionary<String, Value> symbolTable, MyIList<Value> outputConsole, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<Value> heapTable, MyILockTable<Integer> lockTable, MyILatchTable<Integer> latchTable, MyIToySemaphoreTable<Triplet> toySemaphoreTable, MyICountSemaphore<Pair<Integer, List<Integer>>> countSemaphoreTable, MyIBarrierTable<Pair<Integer, List<Integer>>> barrierTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputConsole = outputConsole;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.lockTable = lockTable;
        this.latchTable = latchTable;
        this.toySemaphoreTable = toySemaphoreTable;
        this.countSemaphoreTable = countSemaphoreTable;
        this.barrierTable = barrierTable;
        id = 1;
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

    public MyILockTable<Integer> getLockTable() {
        return lockTable;
    }

    public void setLockTable(MyILockTable<Integer> lockTable) {
        this.lockTable = lockTable;
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
                " --------  Lock  Table  -------- \n" +
                lockTable.toString() + '\n' +
                " --------  Latch  Table  -------- \n" +
                latchTable.toString() + '\n' +
                " ------  Semaphore  Table1  ------ \n" +
                toySemaphoreTable.toString() + '\n' +
                " ------  Semaphore  Table2  ------ \n" +
                countSemaphoreTable.toString() + '\n' +
                " ------  Barrier  Table  ------ \n" +
                barrierTable.toString() + '\n' +
                " ------------------------------- \n\n\n";
    }
}
