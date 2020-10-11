package Repository;
import Model.Animal;
public class MemoryRepository implements RepositoryInterface {
    private Animal[] animals;
    private int size;
    public MemoryRepository(int capacity) {
        animals = new Animal[capacity];
        size = 0;
    }
    @Override
    public void addElement(Animal animal) throws MyException {
        if (size == animals.length) {
            throw new MyException("Array is full");
        }
        animals[size++] = animal;
    }

    @Override
    public Animal getElementOnPosition(int position) throws MyException {
        if (position >= size) {
            throw new MyException("Invalid position");
        }
        return animals[position];
    }

    @Override
    public Animal[] getElements() {
        return animals;
    }

    @Override
    public void removeElementOnPosition(int position) throws MyException {
        if (position >= size) {
            throw new MyException("Invalid position");
        }
        for (int i = position; i < size - 1; ++i) {
            animals[i] = animals[i + 1];
        }
        animals[size - 1] = null;
        size--;
    }

}
