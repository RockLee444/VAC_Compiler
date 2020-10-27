package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class AutomataController implements Initializable {

    private String[] words;
    private int currentState;
    private int[] finalStates;
    private boolean finish;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Button buttonPlay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentState = 0;
        this.finalStates = new int[]{12, 20, 23, 31, 46, 59, 62, 66, 67};
        addImageToButton();
    }

    @FXML
    void beginVerification(MouseEvent event) {
        String title = "",content = "";
        boolean error = false;
        AlertType alertType = null;
        String text = inputTextArea.getText();
        if( !(text.isEmpty() || text.isBlank()) ){
            verifyInput(text);
        } else {
            title = "ERROR";
            content = "Por favor, no deje el campo en blanco.";
            alertType = AlertType.ERROR;
            error = true;
        }
        if(error){
            showAlert(title,content,alertType);
        }
    }

    public void addImageToButton(){
        Image image = new Image("/images/play_icon.png",26,26,true,true);
        buttonPlay.setGraphic( new ImageView(image) );
    }

    public void verifyInput(String input){
        String[] words = input.split(" ");
        if(words.length == 1){
            words = input.split("\n");
        }
        this.finish = false;
        this.currentState = 0;
        int counter = 0;
        boolean isValid = false;
        String textAlert = "";
    }

    public void showAlert(String title, String content, AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
}
