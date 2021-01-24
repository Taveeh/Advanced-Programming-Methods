import GUI.ChooseProgramController;
import Model.Expression.*;
import Model.Statement.*;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.ReferenceType;
import Model.Types.StringType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.ReferenceValue;
import Model.Values.StringValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    ChooseProgramController chooseProgramController;

    @Override
    public void start(Stage primaryStage) {
        try {
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/ChooseProgramStyle.fxml"));
            GridPane root = (GridPane) loader.load();
            chooseProgramController = loader.getController();
            addStatementsToController();
            chooseProgramController.setMenuStage(primaryStage);
            Scene scene = new Scene(root, screenBounds.getWidth() / 3, screenBounds.getHeight());
            scene.getStylesheets().add(getClass().getResource("GUI/application.css").toExternalForm());
            primaryStage.setTitle("Toy Language Menu");
            primaryStage.setScene(scene);
            primaryStage.setX(0);
            primaryStage.setY(0);
            primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("GUI/pokemon.png")));

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addStatementsToController() {
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
        chooseProgramController.addStatement(example12);
        chooseProgramController.addStatement(example13);
        chooseProgramController.addStatement(example14);
        chooseProgramController.addStatement(example15);
        chooseProgramController.addStatement(example16);
        chooseProgramController.addStatement(example17);
        chooseProgramController.addStatement(example18);

    }

    private static final IStatement example1; // basic

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

    private static final IStatement example2; // operations

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

    private static final IStatement example3; // if

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

    private static final IStatement example4; // files + string

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

    private static final IStatement example5; // read/write heap

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

    private static final IStatement example6; // read/write heap

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

    private static final IStatement example7; // while

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

    private static final IStatement example8; // read/write heap

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

    private static final IStatement example9; // read/write heap

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

    private static final IStatement example10; // fork

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

    private static final IStatement example11; // type checking failed

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

    private static final IStatement example12; // for

    static {
        example12 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "a",
                                new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(
                                new ForStatement(
                                        new ValueExpression(new IntegerValue(0)),
                                        new ValueExpression(new IntegerValue(3)),
                                        new ArithmeticExpression(
                                                '+',
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntegerValue(1))
                                        ),
                                        new ForkStatement(new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                '*',
                                                                new VariableExpression("v"),
                                                                new ReadHeapExpression(new VariableExpression("a"))
                                                        )
                                                )
                                        ))
                                ),
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                        )

                )
        );
    }

    private static final IStatement example13; // conditional assignment

    static {
        example13 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new ReferenceType(new IntegerType())
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "b",
                                new ReferenceType(new IntegerType())
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "v",
                                        new IntegerType()
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new ValueExpression(new IntegerValue(0))
                                        ),
                                        new CompoundStatement(
                                                new AllocateHeapStatement(
                                                        "b",
                                                        new ValueExpression(new IntegerValue(0))
                                                ),
                                                new CompoundStatement(
                                                        new WriteHeapStatement("a",
                                                                new ValueExpression(new IntegerValue(1))
                                                        ),
                                                        new CompoundStatement(
                                                                new WriteHeapStatement(
                                                                        "b",
                                                                        new ValueExpression(new IntegerValue(2))
                                                                ),
                                                                new CompoundStatement(
                                                                        new ConditionalAssignmentStatement(
                                                                                "v",
                                                                                new RelationalExpression(
                                                                                        "<",
                                                                                        new ReadHeapExpression(new VariableExpression("a")),
                                                                                        new ReadHeapExpression(new VariableExpression("b"))
                                                                                ),
                                                                                new ValueExpression(new IntegerValue(100)),
                                                                                new ValueExpression(new IntegerValue(200))
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new CompoundStatement(
                                                                                        new ConditionalAssignmentStatement(
                                                                                                "v",
                                                                                                new RelationalExpression(
                                                                                                        ">",
                                                                                                        new ArithmeticExpression(
                                                                                                                '-',
                                                                                                                new ReadHeapExpression(new VariableExpression("b")),
                                                                                                                new ValueExpression(new IntegerValue(2))
                                                                                                        ),
                                                                                                        new ReadHeapExpression(new VariableExpression("a"))
                                                                                                ),
                                                                                                new ValueExpression(new IntegerValue(100)),
                                                                                                new ValueExpression(new IntegerValue(200))
                                                                                        ),
                                                                                        new PrintStatement(new VariableExpression("v"))
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

    private static final IStatement example14; // switch

    static {
        example14 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "b",
                                new IntegerType()
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "c",
                                        new IntegerType()
                                ),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "a",
                                                new ValueExpression(new IntegerValue(1))
                                        ),
                                        new CompoundStatement(
                                                new AssignStatement(
                                                        "b",
                                                        new ValueExpression(new IntegerValue(2))
                                                ),
                                                new CompoundStatement(
                                                        new AssignStatement(
                                                                "c",
                                                                new ValueExpression(new IntegerValue(5))
                                                        ),
                                                        new CompoundStatement(
                                                                new SwitchStatement(
                                                                        new ArithmeticExpression(
                                                                                '*',
                                                                                new VariableExpression("a"),
                                                                                new ValueExpression(new IntegerValue(10))
                                                                        ),
                                                                        new ArithmeticExpression(
                                                                                '*',
                                                                                new VariableExpression("b"),
                                                                                new VariableExpression("c")
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("a")),
                                                                                new PrintStatement(new VariableExpression("b"))
                                                                        ),
                                                                        new ValueExpression(new IntegerValue(10)),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new ValueExpression(new IntegerValue(100))),
                                                                                new PrintStatement(new ValueExpression(new IntegerValue(200)))
                                                                        ),
                                                                        new PrintStatement(new ValueExpression(new IntegerValue(300)))
                                                                ),
                                                                new PrintStatement(new ValueExpression(new IntegerValue(300)))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example15; // sleep

    static {
        example15 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(new IntegerValue(10))
                        ),
                        new CompoundStatement(
                                new ForkStatement(
                                        new CompoundStatement(
                                                new AssignStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                '-',
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntegerValue(1))
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new AssignStatement(
                                                                "v",
                                                                new ArithmeticExpression(
                                                                        '-',
                                                                        new VariableExpression("v"),
                                                                        new ValueExpression(new IntegerValue(1))
                                                                )
                                                        ),
                                                        new PrintStatement(new VariableExpression("v"))
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new SleepStatement(new ValueExpression(new IntegerValue(10))),
                                        new PrintStatement(new ArithmeticExpression('*', new VariableExpression("v"), new ValueExpression(new IntegerValue(10))))

                                )
                        )

                )
        );
    }

    private static final IStatement example16; // repeat until

    static {
        example16 = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(new IntegerValue(0))
                        ),
                        new CompoundStatement(
                                new RepeatUntilStatement(
                                        new RelationalExpression(
                                                "==",
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntegerValue(3))
                                        ),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("v")),
                                                                new AssignStatement(
                                                                        "v",
                                                                        new ArithmeticExpression(
                                                                                '-',
                                                                                new VariableExpression("v"),
                                                                                new ValueExpression(new IntegerValue(1))
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                '+',
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntegerValue(1))
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(new ArithmeticExpression('*', new VariableExpression("v"), new ValueExpression(new IntegerValue(10))))
                        )
                )
        );
    }

    private static final IStatement example17; // lock/unlock

    static {
        example17 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v1",
                        new ReferenceType(new IntegerType())
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "v2",
                                new ReferenceType(new IntegerType())
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement("x", new IntegerType()),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("q", new IntegerType()),
                                        new CompoundStatement(
                                                new AllocateHeapStatement("v1", new ValueExpression(new IntegerValue(20))),
                                                new CompoundStatement(
                                                        new AllocateHeapStatement("v2", new ValueExpression(new IntegerValue(30))),
                                                        new CompoundStatement(
                                                                new NewLockStatement("x"),
                                                                new CompoundStatement(
                                                                        new ForkStatement(
                                                                                new CompoundStatement(
                                                                                        new ForkStatement(
                                                                                                new CompoundStatement(
                                                                                                        new LockStatement("x"),
                                                                                                        new CompoundStatement(
                                                                                                                new WriteHeapStatement(
                                                                                                                        "v1",
                                                                                                                        new ArithmeticExpression(
                                                                                                                                '-',
                                                                                                                                new ReadHeapExpression(new VariableExpression("v1")),
                                                                                                                                new ValueExpression(new IntegerValue(1))
                                                                                                                        )
                                                                                                                ),
                                                                                                                new UnlockStatement("x")
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompoundStatement(
                                                                                                new LockStatement("x"),
                                                                                                new CompoundStatement(
                                                                                                        new WriteHeapStatement(
                                                                                                                "v1",
                                                                                                                new ArithmeticExpression(
                                                                                                                        '*',
                                                                                                                        new ReadHeapExpression(new VariableExpression("v1")),
                                                                                                                        new ValueExpression(new IntegerValue(10))
                                                                                                                )
                                                                                                        ),
                                                                                                        new UnlockStatement("x")
                                                                                                )
                                                                                        )
                                                                                )
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new NewLockStatement("q"),
                                                                                new CompoundStatement(
                                                                                        new ForkStatement(
                                                                                                new CompoundStatement(
                                                                                                        new ForkStatement(
                                                                                                                new CompoundStatement(
                                                                                                                        new LockStatement("q"),
                                                                                                                        new CompoundStatement(
                                                                                                                                new WriteHeapStatement(
                                                                                                                                        "v2",
                                                                                                                                        new ArithmeticExpression(
                                                                                                                                                '+',
                                                                                                                                                new ReadHeapExpression(new VariableExpression("v2")),
                                                                                                                                                new ValueExpression(new IntegerValue(5))
                                                                                                                                        )
                                                                                                                                ),
                                                                                                                                new UnlockStatement("q")
                                                                                                                        )
                                                                                                                )
                                                                                                        ),
                                                                                                        new CompoundStatement(
                                                                                                                new LockStatement("q"),
                                                                                                                new CompoundStatement(
                                                                                                                        new WriteHeapStatement(
                                                                                                                                "v2",
                                                                                                                                new ArithmeticExpression(
                                                                                                                                        '*',
                                                                                                                                        new ReadHeapExpression(new VariableExpression("v2")),
                                                                                                                                        new ValueExpression(new IntegerValue(10))
                                                                                                                                )
                                                                                                                        ),
                                                                                                                        new UnlockStatement("q")
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompoundStatement(
                                                                                                new NopStatement(),
                                                                                                new CompoundStatement(
                                                                                                        new NopStatement(),
                                                                                                        new CompoundStatement(
                                                                                                                new NopStatement(),
                                                                                                                new CompoundStatement(
                                                                                                                        new NopStatement(),
                                                                                                                        new CompoundStatement(
                                                                                                                                new LockStatement("x"),
                                                                                                                                new CompoundStatement(
                                                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                                                        new CompoundStatement(
                                                                                                                                                new UnlockStatement("x"),
                                                                                                                                                new CompoundStatement(
                                                                                                                                                        new LockStatement("q"),
                                                                                                                                                        new CompoundStatement(
                                                                                                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v2"))),
                                                                                                                                                                new UnlockStatement("q")
                                                                                                                                                        )
                                                                                                                                                )
                                                                                                                                        )
                                                                                                                                )
                                                                                                                        )
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
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

    private static final IStatement example18; //countdown latch

    static {
        example18 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v1",
                        new ReferenceType(new IntegerType())
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "v2",
                                new ReferenceType(new IntegerType())
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "v3",
                                        new ReferenceType(new IntegerType())
                                ),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(
                                                "cnt",
                                                new IntegerType()
                                        ),
                                        new CompoundStatement(
                                                new AllocateHeapStatement("v1", new ValueExpression(new IntegerValue(2))),
                                                new CompoundStatement(
                                                        new AllocateHeapStatement("v2", new ValueExpression(new IntegerValue(3))),
                                                        new CompoundStatement(
                                                                new AllocateHeapStatement("v3", new ValueExpression(new IntegerValue(4))),
                                                                new CompoundStatement(
                                                                        new NewLatchStatement("cnt", new ReadHeapExpression(new VariableExpression("v2"))),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new WriteHeapStatement("v1", new ArithmeticExpression(
                                                                                                        '*',
                                                                                                        new ReadHeapExpression(new VariableExpression("v1")),
                                                                                                        new ValueExpression(new IntegerValue(10))
                                                                                                )),
                                                                                                new CompoundStatement(
                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                        new CompoundStatement(
                                                                                                                new CountDownStatement("cnt"),
                                                                                                                new ForkStatement(
                                                                                                                        new CompoundStatement(
                                                                                                                                new WriteHeapStatement("v2", new ArithmeticExpression(
                                                                                                                                        '*',
                                                                                                                                        new ReadHeapExpression(new VariableExpression("v2")),
                                                                                                                                        new ValueExpression(new IntegerValue(10))
                                                                                                                                )),
                                                                                                                                new CompoundStatement(
                                                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v2"))),
                                                                                                                                        new CompoundStatement(
                                                                                                                                                new CountDownStatement("cnt"),
                                                                                                                                                new ForkStatement(
                                                                                                                                                        new CompoundStatement(
                                                                                                                                                                new WriteHeapStatement("v3", new ArithmeticExpression(
                                                                                                                                                                        '*',
                                                                                                                                                                        new ReadHeapExpression(new VariableExpression("v3")),
                                                                                                                                                                        new ValueExpression(new IntegerValue(10))
                                                                                                                                                                )),
                                                                                                                                                                new CompoundStatement(
                                                                                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v3"))),
                                                                                                                                                                        new CountDownStatement("cnt")
                                                                                                                                                                )
                                                                                                                                                        )
                                                                                                                                                )
                                                                                                                                        )
                                                                                                                                )
                                                                                                                        )
                                                                                                                )
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new AwaitStatement("cnt"),
                                                                                        new CompoundStatement(
                                                                                                new PrintStatement(new ValueExpression(new IntegerValue(100))),
                                                                                                new CompoundStatement(
                                                                                                        new CountDownStatement("cnt"),
                                                                                                        new PrintStatement(new ValueExpression(new IntegerValue(100)))
                                                                                                )
                                                                                        )
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

    public static void main(String[] args) {
        launch(args);
    }
}

