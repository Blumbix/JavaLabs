package kanban.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kanban.model.Task;

import java.time.LocalDate;


public class TaskEditDialogController {

    @FXML
    public DatePicker dateInput;
    @FXML
    private TextField titleInput;
    @FXML
    private ComboBox<Priority> priorityInput;
    @FXML
    private TextField textInput;



    private Stage dialogStage;
    private Task task;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        priorityInput.getItems().add(Priority.LOW);
        priorityInput.getItems().add(Priority.MEDIUM);
        priorityInput.getItems().add(Priority.HIGH);
        priorityInput.getSelectionModel().selectFirst();
        dateInput.setValue(LocalDate.now());
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setTask(Task task) {
        this.task = task;

        titleInput.setText(task.getTitle());
        priorityInput.setValue(task.getPriority());
        textInput.setText(task.getText());
        dateInput.setValue(task.getDate());
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            task.setTitle(titleInput.getText());
            task.setPriority(priorityInput.getSelectionModel().getSelectedItem());
            task.setText(textInput.getText());
            task.setDate(dateInput.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (titleInput.getText() == null || titleInput.getText().length() == 0) {
            errorMessage += "No valid title!\n";
        }
        
        if (textInput.getText() == null || textInput.getText().length() == 0) {
            errorMessage += "No valid text!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}