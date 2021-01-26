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
    private TableView<Pair<Integer, Integer>> lockTableView;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> lockAddressTableColumn;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> lockIdentifierTableColumn;

    @FXML
    private TableView<Pair<Integer, Integer>> latchTableView;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> latchAddressTableColumn;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> latchIdentifierTableColumn;

    @FXML
    private TableView<Pair<Integer, Pair<Integer, String>>> countSemTableView;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, String>>, Integer> countAddressTableColumn;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, String>>, Integer> countValueTableColumn;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, String>>, String> countListTableColumn;

    @FXML
    private TableView<Pair<Pair<Integer, Integer>, Pair<String, Integer>>> toySemTableView;
    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, Pair<String, Integer>>, Integer> toyAddressTableColumn;
    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, Pair<String, Integer>>, Integer> toyValue1TableColumn;
    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, Pair<String, Integer>>, String> toyListTableColumn;
    @FXML
    private TableColumn<Pair<Pair<Integer, Integer>, Pair<String, Integer>>, Integer> toyValue2TableColumn;

    @FXML
    private TableView<Pair<Integer, Pair<Integer, String>>> barrierTableView;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, String>>, Integer> barrierAddressTableColumn;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, String>>, Integer> barrierValueTableColumn;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, String>>, String> barrierListTableColumn;

    private Controller controller;
    List<ProgramState> programStateList;

    public void setProgramState(IStatement statement, int index) throws InterpreterException {
        String file = "LogExample" + index + ".txt";
        MyIStack<IStatement> stack = new MyStack();
        stack.push(new NopStatement());
        ProgramState state = new ProgramState(
                stack,
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap<>(),
                new MyLockTable<>(),
                new MyLatchTable<>(),
                new MyToySemaphoreTable<>(),
                new MyCountSemaphore<>(),
                new MyBarrierTable<>(),
                statement);
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

    private void populateBarrierTable() {
        List<Pair<Integer, Pair<Integer, String>>> list = new ArrayList<>();
        System.out.println(controller.getBarrierTable());
        controller.getBarrierTable().getContent().forEach((key, value) -> list.add(new Pair<>(key, new Pair<>(value.getKey(), value.getValue().toString()))));
        barrierTableView.setItems(FXCollections.observableList(list));
    }
    private void populateCountSemaphoreTable() {
        List<Pair<Integer, Pair<Integer, String>>> countSemList = new ArrayList<>();
        controller.getCountSemaphoreTable().getContent().forEach((key, value) -> countSemList.add(new Pair<>(key, new Pair<>(value.getKey(), value.getValue().toString()))));
        countSemTableView.setItems(FXCollections.observableList(countSemList));
    }

    private void populateToySemaphoreTable() {
        List<Pair<Pair<Integer, Integer>, Pair<String, Integer>>> list = new ArrayList<>();
        controller.getToySemaphoreTable().getContent().forEach((key, value) -> list.add(new Pair<>(new Pair<>(key, value.first), new Pair<>(value.second.toString(), value.third))));
        toySemTableView.setItems(FXCollections.observableList(list));
    }
    private void populateLockTable() {
        List<Pair<Integer, Integer>> list = new LinkedList<>();
        controller.getLockTable().getContent().forEach((key, value) -> list.add(new Pair<>(key, value)));
        lockTableView.setItems(FXCollections.observableList(list));
    }

    private void setAllFields() {

        populateOutputList();
        populateHeapTable();
        populateSymbolTable();
        populateBarrierTable();
        populateFileList();
        populateToySemaphoreTable();
        populateExecutionList();
        populateLockTable();
        populateLatchTable();
        populateCountSemaphoreTable();
        setNumberOfStates();
        populateProgramList();
    }

    private void populateLatchTable() {
        List<Pair<Integer, Integer>> list = new LinkedList<>();
        controller.getLatchTable().getContent().forEach((key, value) -> list.add(new Pair<>(key, value)));
        latchTableView.setItems(FXCollections.observableList(list));
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
        int numberOfStates = controller.getStateList().size();
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
//        setAllFields();

        oneStep();

        setAllFields();
    }


    @FXML
    private void initialize() {
        heapTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        symbolTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lockTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        latchTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        countSemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        toySemTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        barrierTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        countAddressTableColumn.setCellValueFactory(new PairKeyFactory<>());
        countValueTableColumn.setCellValueFactory(new TripleValueKeyFactory<>());
        countListTableColumn.setCellValueFactory(new TripleValueValueFactory<>());

        barrierAddressTableColumn.setCellValueFactory(new PairKeyFactory<>());
        barrierValueTableColumn.setCellValueFactory(new TripleValueKeyFactory<>());
        barrierListTableColumn.setCellValueFactory(new TripleValueValueFactory<>());

        toyAddressTableColumn.setCellValueFactory(new DoublePairKeyKeyFactory<>());
        toyValue1TableColumn.setCellValueFactory(new DoublePairKeyValueFactory<>());
        toyListTableColumn.setCellValueFactory(new DoublePairValueKeyFactory<>());
        toyValue2TableColumn.setCellValueFactory(new DoublePairValueValueFactory<>());

        addressTableColumn.setCellValueFactory(new PairKeyFactory<>());
        valueTableColumn.setCellValueFactory(new PairValueFactory<>());
        variableNameTableColumn.setCellValueFactory(new PairKeyFactory<>());
        variableValueTableColumn.setCellValueFactory(new PairValueFactory<>());
        lockAddressTableColumn.setCellValueFactory(new PairKeyFactory<>());
        lockIdentifierTableColumn.setCellValueFactory(new PairValueFactory<>());
        latchAddressTableColumn.setCellValueFactory(new PairKeyFactory<>());
        latchIdentifierTableColumn.setCellValueFactory(new PairValueFactory<>());
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

class TripleValueValueFactory<T, E, V> implements Callback<TableColumn.CellDataFeatures<Pair<T, Pair<E, V>>, V>, ObservableValue<V>> {
    @Override
    public ObservableValue<V> call(TableColumn.CellDataFeatures<Pair<T, Pair<E, V>>, V> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getValue().getValue());
    }
}

class TripleValueKeyFactory<T, E, V> implements Callback<TableColumn.CellDataFeatures<Pair<T, Pair<E, V>>, E>, ObservableValue<E>> {
    @Override
    public ObservableValue<E> call(TableColumn.CellDataFeatures<Pair<T, Pair<E, V>>, E> data) {
        E value = data.getValue().getValue().getKey();
        if (value instanceof ObservableValue) {
            return (ObservableValue) value;
        } else {
            return new ReadOnlyObjectWrapper<>(value);
        }
    }
}

class DoublePairKeyValueFactory<T, E, V, S> implements Callback<TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, S>, ObservableValue<S>> {
    @Override
    public ObservableValue<S> call(TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, S> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getKey().getValue());
    }
}

class DoublePairValueKeyFactory<T, E, V, S> implements Callback<TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, E>, ObservableValue<E>> {
    @Override
    public ObservableValue<E> call(TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, E> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getValue().getKey());
    }
}

class DoublePairValueValueFactory<T, E, V, S> implements Callback<TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, V>, ObservableValue<V>> {
    @Override
    public ObservableValue<V> call(TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, V> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getValue().getValue());
    }
}
class DoublePairKeyKeyFactory<T, E, V, S> implements Callback<TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, T>, ObservableValue<T>> {
    @Override
    public ObservableValue<T> call(TableColumn.CellDataFeatures<Pair<Pair<T, S>, Pair<E, V>>, T> data) {
        T value = data.getValue().getKey().getKey();
        if (value instanceof ObservableValue) {
            return (ObservableValue) value;
        } else {
            return new ReadOnlyObjectWrapper<>(value);
        }
    }
}

