package GUI;

import Controller.Controller;
import Exceptions.InterpreterException;
import Model.ADTs.*;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Statement.NopStatement;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Callback;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class MainProgramController {
    private int currentState = 1;
    @FXML
    private ListView<String> fileTableListView;
    @FXML
    private ListView<String> outputConsoleListView;
    @FXML
    private ListView<String> executionStackListView;
    @FXML
    private ListView<String> programStatesListView;
    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> addressTableColumn;
    @FXML
    private TextField executionStackTextField;
    @FXML
    private TableView<Pair<String, Value>> symbolTableView;
    @FXML
    private TableColumn<Pair<String, Value>, String> variableNameTableColumn;
    @FXML
    private TableColumn<Pair<String, Value>, Value> variableValueTableColumn;
    @FXML
    private TextField symbolTableTextField;

    @FXML
    private TableColumn<Pair<Integer, Value>, Value> valueTableColumn;

    @FXML
    private TableView<Pair<Integer, Value>> heapTableView;

    @FXML
    private TextField numberOfStatesTextField;

    @FXML
    private TableView<Pair<String, String>> procedureTableView;

    @FXML
    private TableColumn<Pair<String, String>, String> procedureNameTableColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> procedureStatementTableColumn;


    private Controller controller;
    List<ProgramState> programStateList;

    public void setProgramState(IStatement statement, int index) throws InterpreterException {
        String file = "LogExample" + index + ".txt";
        MyIStack<IStatement> stack = new MyStack();
        stack.push(new NopStatement());
        ProgramState state = new ProgramState(stack, new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), new MyHeap<>(), new MyProcedureTable<>(), statement);
        IRepository repository = new Repository(file);
        repository.addState(state);
        controller = new Controller(repository);
        controller.openExecutor();
        programStateList = controller.getStateList();
    }

    private void raiseAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }

    private void oneStep() {
        try {
            controller.conservativeGarbageCollector(programStateList);
            controller.oneStepForAllPrograms(programStateList);
            programStateList = controller.getStateList();
            if (programStateList.size() == 0) {
                controller.closeExecutor();
                controller.setFinalStateList(programStateList);
            }
        } catch (InterpreterException e) {
            raiseAlert("Interpreter: " + e.getMessage());
        } catch (Exception e) {
            raiseAlert(e.getMessage());
        }
    }

    private void setAllFields() {
        setNumberOfStates();
        populateProcedureTable();
        populateHeapTable();
        populateSymbolTable();
        populateProgramList();
        populateFileList();
        populateOutputList();
        populateExecutionList();
    }
    private void populateProcedureTable() {
        List<Pair<String, String>> list = new ArrayList<>();
        controller.getProcedureTable().getContent().forEach((key, value) -> list.add(new Pair<>(key.concat(value.getKey().toString()), value.getValue().toString())));
        procedureTableView.setItems(FXCollections.observableList(list));
    }

    private void populateHeapTable() {
        List<Pair<Integer, Value>> list = new LinkedList<>();
        controller.getHeapTable().getContent().forEach((key, value) -> list.add(new Pair<>(key, value)));
        heapTableView.setItems(FXCollections.observableList(list));
    }

    private void populateExecutionList() {
        executionStackTextField.setText("Execution Stack for " + currentState);
        List<String> list = controller
                .getExecutionStack(currentState)
                .getContent()
                .stream()
                .map(Objects::toString)
                .collect(Collectors.toList());
        Collections.reverse(list);
        executionStackListView.setItems(FXCollections.observableList(list));
    }

    private void populateSymbolTable() {
        symbolTableTextField.setText("Symbol Table for " + currentState);
        List<Pair<String, Value>> list = new LinkedList<>();
        controller.getSymbolTable(currentState)
                .getContent()
                .forEach((key, value) -> list.add(new Pair<>(key, value)));
        symbolTableView.setItems(FXCollections.observableList(list));
    }

    private void populateProgramList() {
        List<String> list = new LinkedList<>();
        controller.getStateList().forEach((el) -> list.add(el.getId().toString()));
        programStatesListView.setItems(FXCollections.observableList(list));
    }

    private void populateFileList() {
        List<String> list = new LinkedList<>();
        controller.getFileTable().getContent().forEach((key, value) -> list.add(key.getValue()));
        fileTableListView.setItems(FXCollections.observableList(list));
    }

    private void populateOutputList() {
        List<String> list = controller
                .getOutput()
                .getContent()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        outputConsoleListView.setItems(FXCollections.observableList(list));
    }

    private void setNumberOfStates() {
        int numberOfStates = controller.numberOfProgramStates();
        if (numberOfStates == 0) {
            numberOfStatesTextField.setText("There are currently no program states");
        } else if (numberOfStates == 1) {
            numberOfStatesTextField.setText("There is currently 1 program state");
        } else {
            numberOfStatesTextField.setText("There are currently " + numberOfStates + " program states");
        }
    }

    @FXML
    private void runOneStep() {
        oneStep();

        setAllFields();
    }


    @FXML
    private void initialize() {
        heapTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        symbolTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        procedureTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        addressTableColumn.setCellValueFactory(new PairKeyFactory<>());
        valueTableColumn.setCellValueFactory(new PairValueFactory<>());
        variableNameTableColumn.setCellValueFactory(new PairKeyFactory<>());
        variableValueTableColumn.setCellValueFactory(new PairValueFactory<>());
        procedureNameTableColumn.setCellValueFactory(new PairKeyFactory<>());
        procedureStatementTableColumn.setCellValueFactory(new PairValueFactory<>());
        programStatesListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try {
                currentState = Integer.parseInt(t1);
            } catch (Exception ignored) {
                return;
            }
            if (currentState == 0) {
                return;
            }
            populateSymbolTable();
            populateExecutionList();
        });
    }
}

class PairKeyFactory<T, E> implements Callback<TableColumn.CellDataFeatures<Pair<T, E>, T>, ObservableValue<T>> {
    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<Pair<T, E>, T> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getKey());
    }
}

class PairValueFactory<T, E> implements Callback<TableColumn.CellDataFeatures<Pair<T, E>, E>, ObservableValue<E>> {
    @Override
    public ObservableValue<E> call(TableColumn.CellDataFeatures<Pair<T, E>, E> data) {
        E value = data.getValue().getValue();
        if (value instanceof ObservableValue) {
            return (ObservableValue) value;
        } else {
            return new ReadOnlyObjectWrapper<>(value);
        }
    }
}


