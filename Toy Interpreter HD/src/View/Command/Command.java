package View.Command;

import Controller.Controller;
public abstract class Command {
    private final String key;
    private final String description;

    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public Controller getController() {
        return null;
    }
    public String getDescription() {
        return description;
    }
    public abstract void execute();
}
