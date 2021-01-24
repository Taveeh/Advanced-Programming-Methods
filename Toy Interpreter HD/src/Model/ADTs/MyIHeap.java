package Model.ADTs;

import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<T> {
    int allocate(T value);
    T get(int address);
    void put(int address, T value);
    T deallocate(int address);
    Map<Integer, T> getContent();
    boolean exists(int address);
    void setContent(Map<Integer, T> map);

}
