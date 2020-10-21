package View;
import Controller.Controller;
import Model.Exceptions.InterpreterException;
import Repository.IRepository;
import Repository.Repository;
import java.util.Scanner;
public class View {
    Controller controller;
    public void printMenu() {
        System.out.println("Welcome to toy language interpreter. Here are basic programs:");
        System.out.println("Example 1:");
        System.out.println("\tint v;\n\tv = 2;\n\t Print(v)\n");
        System.out.println("Example 2:");
        System.out.println("\tint a;\n\ta = 2 + 3 * 5;\n\tb = a + 1;\n\tPrint(b)");
        System.out.println("Example 3:");
        System.out.println("\tbool a;\n\tint v;\n\ta=true\n\t(If a Then v = 2 Else v = 3);\n\tPrint(v)");

        System.out.println("Please enter your wish: ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        controller.newProgram(input);
    }

    public View() {
        IRepository repository = new Repository();
        this.controller = new Controller(repository);
        controller.setDisplayAll(true);
        printMenu();
        try {
            controller.allSteps();
        } catch (InterpreterException e) {
            System.out.println(e.getMessage());
        }
    }
}
