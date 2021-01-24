package Model.ADTs;

import Exceptions.ADTException;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyHeap<T> implements MyIHeap<T> {
    AtomicInteger freeValue;
    Map<Integer, T> heap;

    public MyHeap(HashMap<Integer, T> heap) {
        this.heap = heap;
        freeValue = new AtomicInteger(0);
    }

    public MyHeap() {
        this.heap = new HashMap<>();
        freeValue = new AtomicInteger(0);
    }

    @Override
    public int allocate(T value) {
        heap.put(freeValue.incrementAndGet(), value);

        return freeValue.get();
    }

    @Override
    public T get(int address) {
        return heap.get(address);
    }

    @Override
    public void put(int address, T value) {
        heap.put(address, value);
    }

    @Override
    public T deallocate(int address) {
        return heap.remove(address);
    }

    @Override
    public Map<Integer, T> getContent() {
        return heap;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(var elem: heap.keySet()) {
            if (elem != null)
                s.append(elem.toString()).append(" -> ").append(heap.get(elem).toString()).append('\n');
        }
        return s.toString();
    }

    @Override
    public boolean exists(int address) {
//        System.out.println(address);
//        System.out.println(heap);
        return heap.containsKey(address);
    }

    @Override
    public void setContent(Map<Integer, T> map) {
        heap = map;
    }
}
