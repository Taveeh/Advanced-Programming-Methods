package View;
// 2Y109138
import Controller.Controller;
import Model.Animal;
import Model.Bird;
import Model.Cow;
import Model.Pig;
import Repository.MemoryRepository;
import Repository.RepositoryInterface;

public class AppView {
    public static void main(String[] args) {
        RepositoryInterface repository = new MemoryRepository(10);
        Controller controller = new Controller(repository);
        Bird b = new Bird(150);
        Cow c = new Cow(300);
        Pig p = new Pig(199);
        try {
            controller.addElement(b);
            controller.addElement(c);
            controller.addElement(p);
        }catch (Exception exception) {
            System.out.println(exception.toString());
        }
        Animal[] filtered = controller.filterElements(151);
        for (Animal animal: filtered) {
            if (animal != null) {
                System.out.println(animal.toString());
            }
        }

        try {
            controller.deleteElement(c);
        }catch (Exception exception) {
            System.out.println(exception.toString());
        }
        filtered = controller.filterElements(149);
        for (Animal animal: filtered) {
            if (animal != null) {
                System.out.println(animal.toString());
            }
        }
    }
}
