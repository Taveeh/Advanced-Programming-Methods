package Model.ADTs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class MyLockTable<T> implements MyILockTable<T> {
    AtomicInteger freeValue;
    Map<Integer, T> lockTable;

    public MyLockTable() {
        this.lockTable = new HashMap<>();
        freeValue = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        lockTable.put(freeValue.incrementAndGet(), value);
        return freeValue.get();
    }

    @Override
    public synchronized void update(int address, T value) {
        lockTable.put(address, value);
    }

    @Override
    public Map<Integer, T> getContent() {
        return lockTable;
    }

    @Override
    public boolean exists(int address) {
        return lockTable.containsKey(address);
    }

    @Override
    public void setContent(Map<Integer, T> map) {
        lockTable = map;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: lockTable.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(lockTable.get(elem).toString()).append('\n');
        }
        return s.toString();
    }
}
