package Model.ADTs;

import java.util.Map;

public interface MyILatchTable<T> {
    int allocate(T value);
    void update(int address, T value);
    Map<Integer, T> getContent();
    boolean exists(int address);
    void setContent(Map<Integer, T> map);
    T get(int addr);
}
