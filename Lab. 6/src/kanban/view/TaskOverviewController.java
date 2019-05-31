package kanban.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import kanban.MainApp;
import kanban.model.Task;

import javax.swing.text.html.ImageView;

public class TaskOverviewController {

    @FXML
    private TableView<Task> toDoTable;
    @FXML
    private TableColumn<Task, String> toDoColumn;
    @FXML
    private TableColumn<Task, ImageView> priorityToDoImage;

    @FXML
    private TableView<Task> inProgressTable;
    @FXML
    private TableColumn<Task, String> inProgressColumn;

    @FXML
    private TableView<Task> doneTable;
    @FXML
    private TableColumn<Task,String> doneColumn;

    private MainApp mainApp;


    @FXML
    private void initialize() {
        toDoColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        inProgressColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        doneColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());


        // context menu
        toDoTable.setRowFactory(
                tableView -> {
                    final TableRow<Task> row = new TableRow<>();
                    final ContextMenu rowMenu = new ContextMenu();
                    MenuItem editItem = new MenuItem("Edit");
                    editItem.setOnAction(e -> {
                        Task selectedTask = toDoTable.getSelectionModel().getSelectedItem();
                        if (selectedTask != null) {
                            mainApp.showTaskEditDialog(selectedTask);
                    }});
                        MenuItem removeItem = new MenuItem("Delete");
                    removeItem.setOnAction(event -> toDoTable.getItems().remove(row.getItem()));
                    rowMenu.getItems().addAll(editItem, removeItem);

                    row.contextMenuProperty().bind(
                            Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                    .then(rowMenu)
                                    .otherwise((ContextMenu)null));
                    return row;
                });

        toDoTable.getColumns().forEach(this::addTooltipToColumnCells);
    }

    private <T> void addTooltipToColumnCells(TableColumn<Task,T> column) {

        Callback<TableColumn<Task, T>, TableCell<Task,T>> existingCellFactory
                = column.getCellFactory();

        column.setCellFactory(c -> {
            TableCell<Task, T> cell = existingCellFactory.call(c);

            Tooltip tooltip = new Tooltip();

            // todo: 'null' in empty cells
            if (cell.itemProperty().asString().toString().equals("null"))
                tooltip.textProperty().bind(new SimpleStringProperty(""));
            else
                tooltip.textProperty().bind(cell.itemProperty().asString());

            cell.setTooltip(tooltip);
            return cell ;
        });
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        toDoTable.setItems(mainApp.getTaskData());
    }

    @FXML
    private void handleNewTask() {
        Task tempTask = new Task();
        boolean okClicked = mainApp.showTaskEditDialog(tempTask);
        if (okClicked) {
            mainApp.getTaskData().add(tempTask);
        }
    }

    public void moveNextHandler() {
        Task todoToInProgress = toDoTable.getSelectionModel().getSelectedItem();
        if (todoToInProgress != null) {
            inProgressTable.getItems().add(todoToInProgress);
            toDoTable.getItems().remove(todoToInProgress);
        }

        Task inProgressToDone = inProgressTable.getSelectionModel().getSelectedItem();
        if (inProgressToDone != null) {
            doneTable.getItems().add(inProgressToDone);
            inProgressTable.getItems().remove(inProgressToDone);
        }
    }


    public void movePrevHandler() {

        // todo: change - many selections at the same time on several boards
        Task inProgressToToDo = inProgressTable.getSelectionModel().getSelectedItem();
        if (inProgressToToDo != null) {
            inProgressTable .getItems().remove(inProgressToToDo);
            toDoTable.getItems().add(inProgressToToDo);
        }

        Task doneToInProgress = doneTable.getSelectionModel().getSelectedItem();
        if (doneToInProgress != null) {
            doneTable.getItems().remove(doneToInProgress);
            inProgressTable.getItems().add(doneToInProgress);
        }
    }
}