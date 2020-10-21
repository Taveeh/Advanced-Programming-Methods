package Model.ADTs;

import Model.Exceptions.ADTException;

public interface MyIStack<T> {
    T pop() throws ADTException;
    void push(T elem);
    boolean isEmpty();
}
