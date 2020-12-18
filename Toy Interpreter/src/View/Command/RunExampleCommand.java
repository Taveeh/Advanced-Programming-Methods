package View.Command;

import Controller.Controller;
import Exceptions.InterpreterException;
import Exceptions.TypeException;

public class RunExampleCommand extends Command {
    private final Controller controller;
    @Override
    public void execute() {
        try {
            controller.typeCheck();
            controller.allSteps();
        } catch (InterpreterException exception) {
            if (exception instanceof TypeException) {
                System.out.println("TypeChecking failed: " + exception.getMessage());
            } else {
                System.out.println(exception.getMessage());
            }
        }
    }

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

}
