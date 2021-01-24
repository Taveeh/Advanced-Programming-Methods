//package Tests;
//
//import Exceptions.InterpreterException;
//import Model.ADTs.MyDictionary;
//import Model.ADTs.MyIStack;
//import Model.ADTs.MyList;
//import Model.ADTs.MyStack;
//import Model.Expression.ValueExpression;
//import Model.ProgramState;
//import Model.Statement.*;
//import Model.Types.IntegerType;
//import Model.Values.IntegerValue;
//import Model.Values.Value;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//public class TestClass {
//    @Test
//    public void testPushStatement() {
//        MyIStack<IStatement> stack = new MyStack<>();
//        IStatement statement = new NopStatement();
//        stack.push(statement);
//        ProgramState state = new ProgramState(stack, new MyDictionary<>(), new MyList<>(), new MyDictionary<>());
//        Assertions.assertEquals(state.getExecutionStack().size(), 1);
//    }
//
//    @Test
//    public void testCompoundStatement() throws InterpreterException {
//        MyIStack<IStatement> stack = new MyStack<>();
//        IStatement statement = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()), new AssignStatement("a", new ValueExpression(new IntegerValue(2))));
//        stack.push(statement);
//        ProgramState state = new ProgramState(stack, new MyDictionary<>(), new MyList<>(), new MyDictionary<>());
//        stack.pop();
//        statement.execute(state);
//        Assertions.assertEquals(state.getExecutionStack().size(), 2);
//    }
//
//}
