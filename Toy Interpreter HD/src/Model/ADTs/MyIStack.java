package Model.ADTs;

import Exceptions.ADTException;

import java.util.List;

public interface MyIStack<T> {
    T pop() throws ADTException;
    void push(T elem);
    boolean isEmpty();
    T top() throws ADTException;
    int size();
    List<T> getContent();
}
