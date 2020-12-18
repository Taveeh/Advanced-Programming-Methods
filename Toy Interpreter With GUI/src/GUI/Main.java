package GUI;

import Model.Expression.*;
import Model.Statement.*;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.ReferenceType;
import Model.Types.StringType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.StringValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    ChooseProgramController chooseProgramController;
    @Override
    public void start(Stage primaryStage) {
        try {
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseProgramStyle.fxml"));
            GridPane root = (GridPane) loader.load();
            chooseProgramController = loader.getController();
            addStatementsToController();
            chooseProgramController.setMenuStage(primaryStage);
            Scene scene = new Scene(root, screenBounds.getWidth() / 3, screenBounds.getHeight());
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Toy Language Menu");
            primaryStage.setScene(scene);
            primaryStage.setX(0);
            primaryStage.setY(0);
//            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("pokemonIcon.png")));

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }











    private void addStatementsToController() {
//        ProgramState state1, state2, state3, state4, state5, state6, state7, state8, state9, state10, state11, state12;
//        Controller controller1, controller2, controller3, controller4, controller5, controller6, controller7, controller8, controller9, controller10, controller11, controller12;
//        try {
//            state1 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example1);
//            IRepository repository1 = new Repository("LogExample1.txt");
//            repository1.addState(state1);
//            controller1 = new Controller(repository1);
//
//            state2 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example2);
//            IRepository repository2 = new Repository("LogExample2.txt");
//            repository2.addState(state2);
//            controller2 = new Controller(repository2);
//
//            state3 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example3);
//            IRepository repository3 = new Repository("LogExample3.txt");
//            repository3.addState(state3);
//            controller3 = new Controller(repository3);
//
//            state4 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example4);
//            IRepository repository4 = new Repository("LogExample4.txt");
//            repository4.addState(state4);
//            controller4 = new Controller(repository4);
//
//            state5 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example5);
//            IRepository repository5 = new Repository("LogExample5.txt");
//            repository5.addState(state5);
//            controller5 = new Controller(repository5);
//
//            state6 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example6);
//            IRepository repository6 = new Repository("LogExample6.txt");
//            repository6.addState(state6);
//            controller6 = new Controller(repository6);
//
//            state7 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example7);
//            IRepository repository7 = new Repository("LogExample7.txt");
//            repository7.addState(state7);
//            controller7 = new Controller(repository7);
//
//            state8 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example8);
//            IRepository repository8 = new Repository("LogExample8.txt");
//            repository8.addState(state8);
//            controller8 = new Controller(repository8);
//
//            state9 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example9);
//            IRepository repository9 = new Repository("LogExample9.txt");
//            repository9.addState(state9);
//            controller9 = new Controller(repository9);
//
//            state10 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example10);
//            IRepository repository10 = new Repository("LogExample10.txt");
//            repository10.addState(state10);
//            controller10 = new Controller(repository10);
//
//            state11 = new ProgramState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), example11);
//            IRepository repository11 = new Repository("LogExample11.txt");
//            repository11.addState(state11);
//            controller11 = new Controller(repository11);
//
//            chooseProgramController.addCommand(new RunExampleCommand("1", example1.toString(), controller1));
//            chooseProgramController.addCommand(new RunExampleCommand("2", example2.toString(), controller2));
//            chooseProgramController.addCommand(new RunExampleCommand("3", example3.toString(), controller3));
//            chooseProgramController.addCommand(new RunExampleCommand("4", example4.toString(), controller4));
//            chooseProgramController.addCommand(new RunExampleCommand("5", example5.toString(), controller5));
//            chooseProgramController.addCommand(new RunExampleCommand("6", example6.toString(), controller6));
//            chooseProgramController.addCommand(new RunExampleCommand("7", example7.toString(), controller7));
//            chooseProgramController.addCommand(new RunExampleCommand("8", example8.toString(), controller8));
//            chooseProgramController.addCommand(new RunExampleCommand("9", example9.toString(), controller9));
//            chooseProgramController.addCommand(new RunExampleCommand("10", example10.toString(), controller10));
//            chooseProgramController.addCommand(new RunExampleCommand("11", example11.toString(), controller11));
//
//        } catch (InterpreterException exception) {
//            System.out.println(exception.getMessage());
//        }
        chooseProgramController.addStatement(example1);
        chooseProgramController.addStatement(example2);
        chooseProgramController.addStatement(example3);
        chooseProgramController.addStatement(example4);
        chooseProgramController.addStatement(example5);
        chooseProgramController.addStatement(example6);
        chooseProgramController.addStatement(example7);
        chooseProgramController.addStatement(example8);
        chooseProgramController.addStatement(example9);
        chooseProgramController.addStatement(example10);
        chooseProgramController.addStatement(example11);

    }
    private static final IStatement example1;

    static {
        example1 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(2)
                                )
                        ),
                        new PrintStatement(
                                new VariableExpression(
                                        "v"
                                )
                        )
                )
        );
    }

    private static final IStatement example2;

    static {
        example2 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement(
                        "b",
                        new IntegerType()
                ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "a",
                                        new ArithmeticExpression(
                                                '+',
                                                new ValueExpression(
                                                        new IntegerValue(2)
                                                ),
                                                new ArithmeticExpression(
                                                        '*',
                                                        new ValueExpression(
                                                                new IntegerValue(3)
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "b",
                                                new ArithmeticExpression(
                                                        '+',
                                                        new VariableExpression("a"),
                                                        new ValueExpression(
                                                                new IntegerValue(1)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("b")
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example3;

    static {
        example3 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new BooleanType()
                ),
                new CompoundStatement(new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "a",
                                        new ValueExpression(
                                                new BooleanValue(true)
                                        )
                                ),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(2)
                                                        )
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(3)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("v")
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example4;

    static {
        example4 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "varf",
                        new StringType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "varf",
                                new ValueExpression(
                                        new StringValue("test.in")
                                )
                        ),
                        new CompoundStatement(
                                new OpenReadFileStatement(
                                        new VariableExpression(
                                                "varf"
                                        )
                                ),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(
                                                "varc",
                                                new IntegerType()
                                        ),
                                        new CompoundStatement(
                                                new ReadFileStatement(
                                                        new VariableExpression(
                                                                "varf"
                                                        ),
                                                        "varc"
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(
                                                                new VariableExpression(
                                                                        "varc"
                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(
                                                                        new VariableExpression(
                                                                                "varf"
                                                                        ),
                                                                        "varc"
                                                                ),
                                                                new CompoundStatement(
                                                                        new PrintStatement(
                                                                                new VariableExpression(
                                                                                        "varc"
                                                                                )
                                                                        ),
                                                                        new CloseReadFileExpression(
                                                                                new VariableExpression(
                                                                                        "varf"
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example5;

    static {
        example5 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new PrintStatement(
                                        new ReadHeapExpression(
                                                new VariableExpression(
                                                        "v"
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new WriteHeapStatement(
                                                "v",
                                                new ValueExpression(
                                                        new IntegerValue(30)
                                                )
                                        ),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        '+',
                                                        new ReadHeapExpression(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example6;

    static {
        example6 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "a",
                                        new ReferenceType(
                                                new ReferenceType(
                                                        new IntegerType()
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new VariableExpression("v")
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new VariableExpression("v")
                                                        )

                                                ),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example7;

    static {
        example7 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(4)
                                )
                        ),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                ">",
                                                new VariableExpression("v"),
                                                new ValueExpression(
                                                        new IntegerValue(0)
                                                )
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(
                                                        new VariableExpression("v")
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                '-',
                                                                new VariableExpression("v"),
                                                                new ValueExpression(
                                                                        new IntegerValue(1)
                                                                )
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(
                                        new VariableExpression("v")
                                )
                        )
                )
        );
    }

    private static final IStatement example8;

    static {
        example8 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "a",
                                        new ReferenceType(
                                                new ReferenceType(
                                                        new IntegerType()
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new VariableExpression("v")
                                        ),
                                        new CompoundStatement(
                                                new AllocateHeapStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(30)
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example9;

    static {
        example9 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new PrintStatement(
                                        new ReadHeapExpression(
                                                new VariableExpression(
                                                        "v"
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "v",
                                                new ValueExpression(
                                                        new IntegerValue(30)
                                                )
                                        ),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        '+',
                                                        new ReadHeapExpression(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example10;

    static {
        example10 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "a",
                                new ReferenceType(
                                        new IntegerType()
                                )
                        ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "v",
                                        new ValueExpression(
                                                new IntegerValue(10)
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new ValueExpression(
                                                        new IntegerValue(22)
                                                )
                                        ),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new WriteHeapStatement(
                                                                        "a",
                                                                        new ValueExpression(
                                                                                new IntegerValue(30)
                                                                        )
                                                                ),
                                                                new CompoundStatement(
                                                                        new AssignStatement(
                                                                                "v",
                                                                                new ValueExpression(
                                                                                        new IntegerValue(32)
                                                                                )
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(
                                                                                        new VariableExpression("v")
                                                                                ),
                                                                                new PrintStatement(
                                                                                        new ReadHeapExpression(
                                                                                                new VariableExpression(
                                                                                                        "a"
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new PrintStatement(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example11;

    static {
        example11 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new BooleanValue(true)
                                )
                        ),
                        new PrintStatement(
                                new VariableExpression(
                                        "v"
                                )
                        )
                )
        );
    }

    public static void main(String[] args) {


        launch(args);
    }
}

