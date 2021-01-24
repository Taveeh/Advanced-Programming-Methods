package View;

import View.Command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private final Map<String, Command> commandMap;

    public TextMenu() {
        commandMap = new HashMap<>();
    }

    public void addCommand(Command command) {
        commandMap.put(command.getKey(), command);
    }
    private void printMenu() {
        for (Command command: commandMap.values()) {
            String line = String.format("%4s:%s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
        System.out.println("Note: every example can be run only one");
    }
    private void removeCommand(Command command) {
        commandMap.remove(command.getKey());
    }
    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Please enter your wish: ");
            String key = scanner.nextLine();
            Command command = commandMap.get(key);
            if (command == null) {
                System.out.println("Invalid option");
                continue;
            }
            command.execute();
            if (commandMap.isEmpty()) {
                break;
            }
        }
    }
}
