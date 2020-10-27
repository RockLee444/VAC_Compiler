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
        keywords.put("clear","Palabra reservada del sistema");
        keywords.put("condition","Palabra reservada del sistema");
        keywords.put("data","Palabra reservada del sistema");
        keywords.put("enter","Palabra reservada del sistema");
        keywords.put("fail","Palabra reservada del sistema");
        keywords.put("ignore","Palabra reservada del sistema");
        keywords.put("memorize","Palabra reservada del sistema");
        keywords.put("output","Palabra reservada del sistema");
        keywords.put("program","Palabra reservada del sistema");
        keywords.put("rnumber","Palabra reservada del sistema");
        keywords.put("stepto","Palabra reservada del sistema");
        keywords.put("stop","Palabra reservada del sistema");
        keywords.put("supersede","Palabra reservada del sistema");
    }

    public void fillSymbols(){
        keywords.put(";","Símbolo de término de línea de código");
        keywords.put("#","Símbolo para agregar comentarios en una única línea de código");
        keywords.put("{","Símbolo para identificar el inicio de una función o valores dentro de un arreglo");
        keywords.put("}","Símbolo para identificar el final de una función o valores dentro de un arreglo");
        keywords.put("(","Símbolo para identificar el inicio de los argumentos a agregar");
        keywords.put(")","Símbolo para identificar el fin de los argumentos a agregar");
        keywords.put("\"","Símbolo para identificar cadenas de texto");
        keywords.put("+","Símbolo de concatenación y/o suma");
        keywords.put("-","Símbolo de resta");
        keywords.put("*","Símbolo de multiplicación");
        keywords.put("/","Símbolo de división");
        keywords.put("=","Símbolo de asignación de valores");
        keywords.put("==","Símbolo de comparación");
        keywords.put(">","Símbolo de desigualdad entre dos valores, donde el valor primero es mayor al segundo");
        keywords.put("<","Símbolo de desigualdad entre dos valores, donde el valor primero es menor al segundo");
        keywords.put(">=","Símbolo de condición, en caso de que valor sera mayor o igual a otro");
        keywords.put("<=","Símbolo de condición, en caso de que valor sera menor o igual a otro");
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
