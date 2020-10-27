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
import java.util.HashMap;
import java.util.ResourceBundle;

public class AutomataController implements Initializable {

    private String[] words;
    private boolean finish;
    private HashMap<String,String> keywords;
    private HashMap<String,String> symbols;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Button buttonPlay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keywords = new HashMap<>();
        symbols = new HashMap<>();
        fillKeywords();
        fillSymbols();
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
        this.finish = false;
        int counter = 0;
        boolean isValid = false;
        String textAlert = "";
        this.words = input.split("\n");

        String result = keywords.get(input);
        for(int i=0;i<words.length;i++){
            System.out.println("__________________________________________________");
            System.out.println("RESULT WORD " + words[i]);
            String output = keywords.get(words[i]);
            if(output == null){
                output = symbols.get(words[i]);
            }
            System.out.println("RESULT: " + output);
        }
    }

    public void fillKeywords(){
        keywords.put("clear","REGEX");
        keywords.put("condition","REGEX");
        keywords.put("data","REGEX");
        keywords.put("enter","REGEX");
        keywords.put("fail","REGEX");
        keywords.put("ignore","REGEX");
        keywords.put("memorize","REGEX");
        keywords.put("output","REGEX");
        keywords.put("program","REGEX");
        keywords.put("rnumber","REGEX");
        keywords.put("stepto","REGEX");
        keywords.put("stop","REGEX");
        keywords.put("supersede","REGEX");
    }

    public void fillSymbols(){
        keywords.put(";","REGEX");
        keywords.put("#","REGEX");
        keywords.put("{","REGEX");
        keywords.put("}","REGEX");
        keywords.put("(","REGEX");
        keywords.put(")","REGEX");
        keywords.put("\"","REGEX");
        keywords.put("+","REGEX");
        keywords.put("-","REGEX");
        keywords.put("*","REGEX");
        keywords.put("/","REGEX");
        keywords.put("=","REGEX");
        keywords.put("==","REGEX");
        keywords.put(">","REGEX");
        keywords.put("<","REGEX");
        keywords.put(">=","REGEX");
        keywords.put("<=","REGEX");
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
