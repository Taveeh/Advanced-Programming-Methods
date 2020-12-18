package Model.ADTs;

import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<T> {
    public int allocate(T value);
    public T get(int address);
    public void put(int address, T value);
    public T deallocate(int address);
    public Map<Integer, T> getContent();
    public boolean exists(int address);
    public void setContent(Map<Integer, T> map);

}
