package Model.ADTs;

import java.util.List;

public interface MyIList<T> {
    void addElement(T element);
    List<T> getContent();
}
