package Controller;

import Model.ADTs.*;
import Model.Exceptions.ExecutionException;
import Model.Exceptions.InterpreterException;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.Value;
import Repository.IRepository;

import java.util.PropertyResourceBundle;

public class Controller {
    IRepository repository;
    Boolean displayAll;

    public Controller(IRepository repository) {
        this.repository = repository;
        displayAll = false;
    }

    public void setDisplayAll(Boolean displayAll) {
        this.displayAll = displayAll;
    }

    public ProgramState oneStep(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty()) {
            throw new ExecutionException("Stack is empty");
        }
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allSteps() throws InterpreterException{
        ProgramState programState = repository.getCurrentProgram();
        if (displayAll) {
            System.out.println(displayState(programState));
        }
        while (!programState.getExecutionStack().isEmpty()) {
            try {
                oneStep(programState);
                if (displayAll) {
                    System.out.println(displayState(programState));
                }
            } catch (InterpreterException exception) {
                throw new InterpreterException(exception.getMessage());
            }
        }
    }
    public String displayState(ProgramState state) {
        return state.toString();
    }

    public void newProgram(int program) {
        ProgramState state;
        MyIStack<IStatement> stack = new MyStack<IStatement>();
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(2)
                                )
                        ),
                        new PrintStatement(
                                new VariableExpression(
                                        "v"
                                )
                        )
                )
        );
        IStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement(
                        "b",
                        new IntegerType()
                ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "a",
                                        new ArithmeticExpression(
                                                '+',
                                                new ValueExpression(
                                                        new IntegerValue(2)
                                                ),
                                                new ArithmeticExpression(
                                                        '*',
                                                        new ValueExpression(
                                                                new IntegerValue(3)
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "b",
                                                new ArithmeticExpression(
                                                        '+',
                                                        new VariableExpression("a"),
                                                        new ValueExpression(
                                                                new IntegerValue(1)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("b")
                                        )
                                )
                        )
                )
        );
        IStatement ex3 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new BooleanType()
                ),
                new CompoundStatement(new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "a",
                                        new ValueExpression(
                                                new BooleanValue(true)
                                        )
                                ),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(2)
                                                        )
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(3)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("v")
                                        )
                                )
                        )
                )
        );
        switch (program) {
            case 1 -> stack.push(ex1);
            case 2 -> stack.push(ex2);
            case 3 -> stack.push(ex3);
        }

        state = new ProgramState(stack, new MyDictionary<String, Value>(), new MyList<Value>(), null);
        repository.addState(state);

    }

}
