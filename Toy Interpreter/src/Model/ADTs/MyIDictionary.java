package Model.ADTs;

import Model.Exceptions.ADTException;

public interface MyIDictionary<T, E> {
    E lookup(T id);
    Boolean isVariableDefined(T id);
    void update(T id, E value) throws ADTException;
    void add(T id, E value) throws ADTException;
}
