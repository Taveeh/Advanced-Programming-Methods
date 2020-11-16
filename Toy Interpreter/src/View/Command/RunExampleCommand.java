package View.Command;

import Controller.Controller;
import Exceptions.InterpreterException;

public class RunExampleCommand extends Command {
    private final Controller controller;
    @Override
    public void execute() {
        try {
            controller.allSteps();
        } catch (InterpreterException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

}
