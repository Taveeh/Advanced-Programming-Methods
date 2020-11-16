package Model.ADTs;

import Exceptions.ADTException;

import java.util.Map;

public interface MyIDictionary<T, E> {
    E lookup(T id);
    Boolean isVariableDefined(T id);
    void update(T id, E value) throws ADTException;
    void add(T id, E value) throws ADTException;
    void remove(T id) throws ADTException;
    Map<T, E> getContent();
}
