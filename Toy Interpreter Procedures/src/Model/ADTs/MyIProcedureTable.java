package Model.ADTs;

import java.util.Map;

public interface MyIProcedureTable<T> {
    public T get(String name);
    public void put(String name, T value);
    public Map<String, T> getContent();
    public boolean exists(String name);
    public void setContent(Map<String, T> map);
}
