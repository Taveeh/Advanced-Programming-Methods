package Model.ADTs;

import Model.Exceptions.ADTException;

import java.util.HashMap;

public class MyDictionary<T, E> implements MyIDictionary<T, E> {
    HashMap<T, E> map;
    @Override
    public E lookup(T id) {
        return map.get(id);
    }

    @Override
    public Boolean isVariableDefined(T id) {
        return map.containsKey(id);
    }

    @Override
    public void update(T id, E value) throws ADTException {
        if (!map.containsKey(id)) {
            throw new ADTException("Element does not exist");
        }
        map.replace(id, value);
    }

    @Override
    public void add(T id, E value) throws ADTException {
        if (map.containsKey(id)) {
            throw new ADTException("Element already exists");
        }
        map.put(id, value);
    }

    public MyDictionary() {
        map = new HashMap<T, E>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: map.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(map.get(elem).toString()).append('\n');
        }
        return s.toString();
    }
}
