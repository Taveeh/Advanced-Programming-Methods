package Model.ADTs;

import java.util.LinkedList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    final LinkedList<T> list;

    public MyList() {
        list = new LinkedList<>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T elem: list) {
            if (elem != null) {
                s.append(elem.toString());
                s.append("\n");
            }
        }
        return s.toString();
    }

    @Override
    public void addElement(T element) {
        list.add(element);
    }

    @Override
    public List<T> getContent() {
        return list;
    }
}
