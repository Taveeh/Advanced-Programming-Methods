package Controller;
import Repository.RepositoryInterface;
import Model.Animal;
public class Controller {
    private RepositoryInterface repository;
    public Controller(RepositoryInterface repo) {
        repository = repo;
    }
    public void addElement(Animal animal) throws Exception {
        repository.addElement(animal);
    }
    public Animal[] filterElements(double minWeight) {
        Animal[] filteredAnimals = new Animal[repository.getElements().length];
        int size = 0;
        for (Animal animal: repository.getElements()) {
            if (animal != null && animal.getWeight() >= minWeight) {
                filteredAnimals[size++] = animal;
            }
        }
        return filteredAnimals;
    }
    public void deleteElement(Animal animal) throws Exception{
        for (int i = 0; i < repository.getElements().length; i++) {
            Animal animal1;
            try {
                animal1 = repository.getElementOnPosition(i);
            }catch (Exception exception) {
                throw new Exception("Element does not exist");
            }
            if (animal1.equals(animal)) {
                repository.removeElementOnPosition(i);
                return;
            }
        }
    }
}
