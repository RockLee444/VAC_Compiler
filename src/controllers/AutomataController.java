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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.URL;
import java.util.*;

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

        for(int i=0;i<words.length;i++){
            String fixedString = fixString(words[i]);
            System.out.println("FIXED STRING: "+ fixedString);
            String[] withoutSpaces = fixedString.split(" ");
            for(int j=0;j<withoutSpaces.length;j++){
                System.out.println("_______________________________________");
                String currentWord = withoutSpaces[j];
                if(!currentWord.isBlank() && !currentWord.isEmpty()){
                    System.out.println("CURRENT WORD: " + currentWord);
                    String result = keywords.get(currentWord);
                    System.out.println("RESULT IN KEYWORDS: " + result);
                    if(result == null){
                        result = symbols.get(currentWord);
                        System.out.println("RESULT IN SYMBOLS: " + result);
                        if(result == null){
                            evaluateRegex(currentWord);
                            System.out.println("REGEX");
                        }
                    }
                }
            }
        }
    }

    public void evaluateRegex(String word){
        //TODO Add regex
        boolean isValid = false;
        Pattern palabra = Pattern.compile("^[a-zA-Z]");
        Matcher verificar = palabra.matcher(word);
        if(verificar.matches()){
            isValid = true;
        }
        //esto deberia hacer la comfirmacion, ya ustedes le cambian que si va a retornar algo o no.
    }

    public String fixString(String input){
        Set<String> keys = symbols.keySet();
        Iterator<String> iterator = keys.iterator();
        ArrayList<String> usedStrings = new ArrayList<>();
        String result = input;

        while(iterator.hasNext()){
            boolean hasPassed = false;
            String key = iterator.next();
            String[] keyArray = key.split("");
            for(int i=0;i<keyArray.length;i++){
                if(usedStrings.contains(keyArray[i])){
                    hasPassed = true;
                }
            }
            if(input.contains(key) && !hasPassed){
                boolean noMore = false;
                int position = 0;
                int positionAux = 0;
                while (!noMore) {
                    position = input.indexOf(key,positionAux);
                    if (key.length() > 1) {
                        int position2 = position + 1;
                        result = input.substring(0, position) + " " + key + " " + input.substring(position2 + 1);
                    } else if (position > positionAux){
                        result = input.substring(0, position) + " " + key + input.substring(position, position) + " " + input.substring(position + 1);
                    }else{
                        noMore=true;
                    }
                    positionAux=position+2;
                    position=0;
                    input =  result;
                }
                usedStrings.addAll(Arrays.asList(key.split("")));
                input =  result;
            }
        }

        return result;
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
        symbols.put(";","Símbolo de término de línea de código");
        symbols.put("#","Símbolo para agregar comentarios en una única línea de código");
        symbols.put("{","Símbolo para identificar el inicio de una función o valores dentro de un arreglo");
        symbols.put("}","Símbolo para identificar el final de una función o valores dentro de un arreglo");
        symbols.put("(","Símbolo para identificar el inicio de los argumentos a agregar");
        symbols.put(")","Símbolo para identificar el fin de los argumentos a agregar");
        symbols.put("\"","Símbolo para identificar cadenas de texto");
        symbols.put("+","Símbolo de concatenación y/o suma");
        symbols.put("-","Símbolo de resta");
        symbols.put("*","Símbolo de multiplicación");
        symbols.put("/","Símbolo de división");
        symbols.put("=","Símbolo de asignación de valores");
        symbols.put(">=","Símbolo de condición, en caso de que valor sera mayor o igual a otro");
        symbols.put("<=","Símbolo de condición, en caso de que valor sera menor o igual a otro");
        symbols.put("==","Símbolo de comparación");
        symbols.put(">","Símbolo de desigualdad entre dos valores, donde el valor primero es mayor al segundo");
        symbols.put("<","Símbolo de desigualdad entre dos valores, donde el valor primero es menor al segundo");
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
