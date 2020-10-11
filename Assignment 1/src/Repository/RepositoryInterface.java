package Repository;
import Model.Animal;
public interface RepositoryInterface {
    void addElement(Animal animal) throws MyException;
    Animal getElementOnPosition(int position) throws MyException;
    Animal[] getElements();
    void removeElementOnPosition(int position) throws MyException;
}
