package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private Button runButton;
    @FXML
    private TextField resultBox;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button stopButton;
    @FXML
    private TextField precisionBox;
    @FXML
    private ProgressIndicator pri;


    @FXML
    private void handleRunBtnAction(){
        runButton.setDisable(true);
        int precision=Integer.parseInt(precisionBox.getText());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        final Draw draw=new Draw(gc);
        final MonteCarlo mc=new MonteCarlo(precision,-8,8,258);

        new Thread((Runnable) draw).start();
        new Thread((Runnable) mc).start();
        stopButton.setDisable(false);
        mc.setOnSucceeded(workerStateEvent -> resultBox.setText(""+mc.getValue()));
        mc.setOnCancelled(workerStateEvent -> resultBox.setText("CANCELED"));
        mc.setOnRunning(workerStateEvent -> resultBox.setText("Calculating..."));

        progressBar.progressProperty().bind(mc.progressProperty());
        pri.progressProperty().bind(draw.progressProperty());

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(draw == null || mc == null) {
                    return;
                }
                else{
                    draw.cancel();
                    mc.cancel();
                    stopButton.setDisable(true);
                    runButton.setDisable(false);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.rgb(0,0,0));
        gc.fillRect(gc.getCanvas().getLayoutX(),
                gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());

        stopButton.setDisable(true);
        precisionBox.setText("10000000");
    }
}
