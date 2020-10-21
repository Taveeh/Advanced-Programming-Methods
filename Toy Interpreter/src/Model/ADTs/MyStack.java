package Model.ADTs;
import Model.Exceptions.ADTException;

import java.util.Stack;
public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public T pop() throws ADTException {
        if (stack.isEmpty()) {
            throw new ADTException("Stack is empty");
        }
        return stack.pop();
    }

    @Override
    public void push(T elem) {
//        if (elem != null)
            stack.push(elem);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
