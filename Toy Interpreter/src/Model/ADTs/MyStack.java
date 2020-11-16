package Model.ADTs;
import Exceptions.ADTException;
import Model.Statement.IStatement;

import java.util.ListIterator;
import java.util.Stack;
public class MyStack<T> implements MyIStack<T> {
    final Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
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

    public T top() throws ADTException {
        if (stack.isEmpty()) {
            throw new ADTException("Stack is empty");
        }
        return stack.firstElement();
    }

    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        ListIterator<T> stackIterator = stack.listIterator(stack.size());
        while(stackIterator.hasPrevious()) {
            s.append(stackIterator.previous().toString()).append('\n');
        }
        return s.toString();
    }
}
