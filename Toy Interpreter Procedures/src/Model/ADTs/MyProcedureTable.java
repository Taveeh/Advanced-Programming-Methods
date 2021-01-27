package Model.ADTs;

import java.util.HashMap;
import java.util.Map;

public class MyProcedureTable<T> implements MyIProcedureTable<T> {
    Map<String, T> procTable;

    public MyProcedureTable() {
        procTable = new HashMap<>();
    }

    @Override
    public T get(String name) {
        return procTable.get(name);
    }

    @Override
    public void put(String name, T value) {
        procTable.put(name, value);
    }

    @Override
    public Map<String, T> getContent() {
        return procTable;
    }

    @Override
    public boolean exists(String name) {
        return procTable.containsKey(name);
    }

    @Override
    public void setContent(Map<String, T> map) {
        procTable = map;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: procTable.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(procTable.get(elem).toString()).append('\n');
        }
        return s.toString();
    }
}
