package Model.ADTs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCountSemaphore<T> implements MyICountSemaphore<T> {
    AtomicInteger freeValue;
    Map<Integer, T> semTable;

    public MyCountSemaphore() {
        this.semTable = new HashMap<>();
        freeValue = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        semTable.put(freeValue.incrementAndGet(), value);
        return freeValue.get();
    }

    @Override
    public synchronized void update(int address, T value) {
        semTable.put(address, value);
    }

    @Override
    public synchronized Map<Integer, T> getContent() {
        return semTable;
    }

    @Override
    public synchronized boolean exists(int address) {
        return semTable.containsKey(address);
    }

    @Override
    public synchronized void setContent(Map<Integer, T> map) {
        semTable = map;
    }

    @Override
    public synchronized T get(int addr) {
        return semTable.get(addr);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: semTable.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(semTable.get(elem).toString()).append('\n');
        }
        return s.toString();
    }
}
