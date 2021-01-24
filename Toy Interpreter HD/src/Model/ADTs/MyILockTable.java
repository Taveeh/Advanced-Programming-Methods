package Model.ADTs;

import java.util.Map;

public interface MyILockTable<T> {
    int allocate(T value);
    void update(int address, T value);
    Map<Integer, T> getContent();
    boolean exists(int address);
    void setContent(Map<Integer, T> map);
}
