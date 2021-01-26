package Model.ADTs;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBarrierTable<T> implements MyIBarrierTable<T> {
    AtomicInteger freeValue;
    Map<Integer, T> barrierTable;

    public MyBarrierTable() {
        this.barrierTable = new HashMap<>();
        freeValue = new AtomicInteger(0);
    }

    @Override
    public synchronized int allocate(T value) {
        barrierTable.put(freeValue.incrementAndGet(), value);
        return freeValue.get();
    }

    @Override
    public synchronized void update(int address, T value) {
        barrierTable.put(address, value);
    }

    @Override
    public synchronized Map<Integer, T> getContent() {
        return barrierTable;
    }

    @Override
    public synchronized boolean exists(int address) {
        return barrierTable.containsKey(address);
    }

    @Override
    public synchronized void setContent(Map<Integer, T> map) {
        barrierTable = map;
    }

    @Override
    public synchronized T get(int addr) {
        return barrierTable.get(addr);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: barrierTable.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(barrierTable.get(elem).toString()).append('\n');
        }
        return s.toString();
    }
}
