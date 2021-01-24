package Model.Statement;

import Exceptions.InterpreterException;
import Model.ADTs.MyDictionary;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.MyStack;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public class ForkStatement implements IStatement {
    IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public IStatement createCopy() {
        return new ForkStatement(statement);
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIDictionary<String, Value> newSymbolTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymbolTable().getContent().entrySet()) {
            newSymbolTable.add(entry.getKey(), entry.getValue().createCopy());
        }
        MyIStack <IStatement> stack = new MyStack<>();
        stack.push(new NopStatement());
        stack.push(statement);
        ProgramState newProgram = new ProgramState(stack, newSymbolTable, state.getOutputConsole(), state.getFileTable(), state.getHeapTable());
        newProgram.setId();
        return newProgram;
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
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
        statement.typecheck(clone(table));
        return table;
    }
}
