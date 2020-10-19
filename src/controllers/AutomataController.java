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
        int counter = 0;
        this.currentState = 0;
        boolean isValid = false;
        String textAlert = "";

        while (!finish) {
            if (counter > words.length - 1) {
                finish = true;
            } else {
                int i = 0;
                char letter;
                while (i <= words[counter].length() - 1) {
                    letter = words[counter].charAt(i);
                    switch (currentState) {
                        case 0:
                            if (letter == 'c') {
                                currentState = 1;
                            } else if(letter == 'i') {
                                currentState = 4;
                            } else if(letter == 'o') {
                                currentState = 6;
                            } else if(letter == 's') {
                                currentState = 8;
                            } else if(letter == 'm') {
                                currentState = 5;
                            }else if(letter == 'd') {
                                currentState = 2;
                            } else if(letter == 'r') {
                                currentState = 7;
                            } else if(letter == 'e') {
                                currentState = 3;
                            }
                            break;

                        case 1:
                            if (letter == 'l') {
                                currentState = 9;
                            } else if (letter == 'o') {
                                currentState = 13;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 2:
                            if(letter == 'a'){
                                currentState = 21;
                            }
                            break;

                        case 3:
                            if(letter == 'n'){
                                currentState = 24;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 4:
                            if (letter == 'n') {
                                currentState = 32;
                            } else if (letter == 'g') {
                                currentState = 27;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 5:
                            if (letter == 'e') {
                                currentState = 37;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 6:
                            if (letter == 'u') {
                                currentState = 42;
                            } else {
                                currentState = -1;
                            }
                            break;

                        case 7:
                            if(letter == 'n'){
                                currentState = 47;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 8:
                            if (letter == 't') {
                                currentState = 60;
                            } else if (letter == 'u') {
                                currentState = 52;
                            } else {
                                currentState = -1;
                            }
                            break;

                        case 9:
                            if (letter == 'e') {
                                currentState = 10;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 10:
                            if (letter == 'a') {
                                currentState = 11;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 11:
                            if (letter == 'r') {
                                currentState = 12;
                            } else {
                                currentState = - 1;
                            }
                            break;

                        case 12:
                            currentState = -1;
                        break;

                        case 13:
                            if(letter == 'n'){
                                currentState = 14;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 14:
                            if(letter == 'd'){
                                currentState = 15;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 15:
                            if(letter == 'i'){
                                currentState = 16;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 16:
                            if(letter == 't'){
                                currentState = 17;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 17:
                            if(letter == 'i'){
                                currentState = 18;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 18:
                            if(letter == 'o'){
                                currentState = 19;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 19:
                            if(letter == 'n'){
                                currentState = 20;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 20:
                            currentState = -1;
                            break;

                        case 21:
                            if(letter == 't'){
                                currentState = 22;
                            }
                            break;

                        case 22:
                            if(letter == 'a'){
                                currentState = 23;
                            }
                            break;

                        case 23:
                            currentState = -1;
                            break;

                        case 24:
                            if(letter == 't'){
                                currentState = 25;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 25:
                            if(letter == 'e'){
                                currentState = 26;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 26:
                            if(letter == 'r'){
                                currentState = 12;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 27:
                            if(letter == 'n'){
                                currentState = 28;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 28:
                            if(letter == 'o'){
                                currentState = 29;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 29:
                            if(letter == 'r'){
                                currentState = 30;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 30:
                            if(letter == 'e'){
                                currentState = 31;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 31:
                            currentState = -1;
                            break;

                        case 32:
                            if(letter == 'h'){
                                currentState = 33;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 33:
                            if(letter == 'e'){
                                currentState = 34;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 34:
                            if(letter == 'r'){
                                currentState = 35;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 35:
                            if(letter == 'i'){
                                currentState = 36;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 36:
                            if(letter == 't'){
                                currentState = 67;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 37:
                            if(letter == 'm'){
                                currentState = 38;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 38:
                            if(letter == 'o'){
                                currentState = 39;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 39:
                            if(letter == 'r'){
                                currentState = 40;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 40:
                            if(letter == 'i'){
                                currentState = 41;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 41:
                            if(letter == 'z'){
                                currentState = 30;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 42:
                            if(letter == 't'){
                                currentState = 43;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 43:
                            if (letter == 'p') {
                                currentState = 44;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 44:
                            if (letter == 'u') {
                                currentState = 45;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 45:
                            if (letter == 't') {
                                currentState = 46;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 46:
                            currentState = -1;
                            break;

                        case 47:
                            if(letter == 'u'){
                                currentState = 48;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 48:
                            if(letter == 'm'){
                                currentState = 49;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 49:
                            if(letter == 'b'){
                                currentState = 50;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 50:
                            if(letter == 'e'){
                                currentState = 51;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 51:
                            if(letter == 'r'){
                                currentState = 12;
                            }else {
                                currentState = -1;
                            }
                            break;

                        case 52:
                            if (letter == 'p') {
                                currentState = 53;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 53:
                            if (letter == 'e') {
                                currentState = 54;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 54:
                            if (letter == 'r') {
                                currentState = 55;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 55:
                            if (letter == 's') {
                                currentState = 56;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 56:
                            if (letter == 'e') {
                                currentState = 57;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 57:
                            if (letter == 'd') {
                                currentState = 58;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 58:
                            if (letter == 'e') {
                                currentState = 59;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 59:
                            currentState = -1;
                        break;

                        case 60:
                            if (letter == 'o') {
                                currentState = 61;
                            } else if (letter == 'e') {
                                currentState = 63;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 61:
                            if (letter == 'p') {
                                currentState = 62;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 62:
                            currentState = -1;
                            break;

                        case 63:
                            if (letter == 'p') {
                                currentState = 64;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 64:
                            if (letter == 't') {
                                currentState = 65;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 65:
                            if (letter == 'o') {
                                currentState = 66;
                            } else{
                                currentState = -1;
                            }
                            break;

                        case 66:
                            currentState = -1;
                            break;

                        case 67:
                            currentState = -1;
                            break;

                        default:
                            currentState = -1;
                            break;
                    }
                    i++;

                }

                isValid = false;
                for(int z=0;z<finalStates.length;z++){
                    if(currentState == finalStates[z]){
                        isValid = true;
                    }
                }

                textAlert += "La cadena '" + words[counter] + "' ";
                if(isValid){
                    textAlert += "es correcta.\n";
                } else{
                    textAlert += "es incorrecta.\n";
                }

            }
            counter++;
            this.currentState = 0;
        }
        showAlert("HECHO",textAlert,AlertType.CONFIRMATION);
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
