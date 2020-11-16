package Model.ADTs;

import Exceptions.ADTException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<T, E> implements MyIDictionary<T, E> {
    final HashMap<T, E> map;
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
    public Map<T, E> getContent() {
        return map;
    }

    @Override
    public void add(T id, E value) throws ADTException {
        if (map.containsKey(id)) {
            throw new ADTException("Element already exists");
        }
        map.put(id, value);
    }

    @Override
    public void remove(T id) throws ADTException {
        if (map.containsKey(id)) {
            map.remove(id);
        } else {
            throw new ADTException("Element does not exist");
        }
    }

    public MyDictionary() {
        map = new HashMap<>();
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
