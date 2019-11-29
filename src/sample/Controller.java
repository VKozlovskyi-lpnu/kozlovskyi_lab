package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static java.util.stream.Collectors.toMap;

public class Controller implements Initializable {
    public BarChart barchartFX;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public ToggleGroup cipherGroup;
    public RadioButton cesarFX;
    public RadioButton mpFX;
    public RadioButton vigenereFX;
    public TextField textFX;
    public TextField getGramFX;
    public TextField getCountFX;
    public TextField shiftFX;
    public TextField alphabetFX;
    public TextField keyFX;
    public TextArea outputFX;

    public void clearFX(ActionEvent event) {
        barchartFX.getData().clear();
    }

    public void countFX(ActionEvent event) {
        Impl impl = new Impl();
        barchartFX.getData().clear();
        barchartFX.setTitle("Count");
        xAxis.setLabel("Alphabet");
        yAxis.setLabel("Count");
        String text = textFX.getText().toUpperCase();
        XYChart.Series series1 = new XYChart.Series<>();
        Map<Character, Integer> characterIntegerMap = impl.countLetters(text);
        System.out.println(characterIntegerMap);
        characterIntegerMap.forEach((character, integer) -> series1.getData().add(new XYChart.Data<String, Number>(character.toString(), integer)));
        //hack-around:
        //xAxis.setCategories(FXCollections.observableArrayList("Tom", "Andrew", "Patrick"));
        barchartFX.getData().addAll(series1);
    }

    public void sortedFX(ActionEvent event) {
        Impl impl = new Impl();
        barchartFX.getData().clear();
        barchartFX.setTitle("Sorted");
        xAxis.setLabel("Alphabet");
        yAxis.setLabel("Count");
        String text = textFX.getText().toUpperCase();
        XYChart.Series series2 = new XYChart.Series<>();
        Map<Character, Integer> characterIntegerMap = impl.countLetters(text);
        Map<Character, Integer> sorted = characterIntegerMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        System.out.println(sorted);
        sorted.forEach((character, integer) -> series2.getData().add(new XYChart.Data<String, Number>(character.toString(), integer)));
        barchartFX.getData().addAll(series2);
    }

    public void gramFX(ActionEvent event) {
        Impl impl = new Impl();
        barchartFX.getData().clear();
        String text = textFX.getText();
        int gram = Integer.parseInt(getGramFX.getText());
        int count = Integer.parseInt(getCountFX.getText());
        barchartFX.setTitle(gram + " - Gram");
        xAxis.setLabel("Alphabet");
        yAxis.setLabel("Count");
        XYChart.Series series1 = new XYChart.Series<>();
        Map<String, Integer> characterIntegerMap = impl.countDiagrams(text, gram, count);
        System.out.println(characterIntegerMap);
        characterIntegerMap.forEach((String, integer) -> series1.getData().add(new XYChart.Data<String, Number>(String, integer)));
        //hack-around:
        //xAxis.setCategories(FXCollections.observableArrayList("Tom", "Andrew", "Patrick"));
        barchartFX.getData().addAll(series1);
    }

    public void encryptFX(ActionEvent event) {
        if (cipherGroup.getSelectedToggle().equals(cesarFX)) {
            Impl impl = new Impl();
            String text = textFX.getText();
            int count = Integer.parseInt(shiftFX.getText());
            String alphabet = alphabetFX.getText();
            String encryptCesar = impl.encryptCesar(text, count, alphabet);
            outputFX.setText(encryptCesar);
        } else if (cipherGroup.getSelectedToggle().equals(mpFX)) {
            Impl impl = new Impl();
            String text = textFX.getText();
            String alphabet = alphabetFX.getText();
            String key = keyFX.getText();
            String encryptMP = impl.encryptMP(text, alphabet, key);
            outputFX.setText(encryptMP);
        } else if (cipherGroup.getSelectedToggle().equals(vigenereFX)) {
            Impl impl = new Impl();
            String text = textFX.getText();
            String alphabet = alphabetFX.getText();
            String key = keyFX.getText();
            String encryptVigenere = decryptVigenere(text, alphabet, key);
//            String encryptVigenere = impl.encryptVigenere(text, alphabet, key);
            outputFX.setText(encryptVigenere);
        }
    }

    public void decryptFX(ActionEvent event) {
        if (cipherGroup.getSelectedToggle().equals(cesarFX)) {
            Impl impl = new Impl();
            String text = textFX.getText();
            int count = Integer.parseInt(shiftFX.getText());
            String alphabet = alphabetFX.getText();
            String decryptCesar = impl.decryptCesar(text, count, alphabet);
            outputFX.setText(decryptCesar);
        } else if (cipherGroup.getSelectedToggle().equals(mpFX)) {
            Impl impl = new Impl();
            String text = textFX.getText();
            String alphabet = alphabetFX.getText();
            String key = keyFX.getText();
            String decryptMP = impl.decryptMP(text, alphabet, key);
            outputFX.setText(decryptMP);
        } else if (cipherGroup.getSelectedToggle().equals(vigenereFX)) {
            Impl impl = new Impl();
            String text = textFX.getText();
            String alphabet = alphabetFX.getText();
            String key = keyFX.getText();
            String decryptVigenere = impl.decryptVigenere(text, alphabet, key);
            outputFX.setText(decryptVigenere);
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        cipherGroup = new ToggleGroup();
        cesarFX.setToggleGroup(cipherGroup);
        mpFX.setToggleGroup(cipherGroup);
        vigenereFX.setToggleGroup(cipherGroup);
    }

    private String decryptVigenere(String text, String VIGENERE_ALPHABET, String VIGENERE_KEY) {
//        String text = textFX.getText();
//        String VIGENERE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ \'-,.;";
//        String VIGENERE_KEY = "KOZLOVSKIYVOLODYMYR";

//        char[] charsTEXT = text.toUpperCase().toCharArray();
//        char[] charsALPHABET = alphabetx.toUpperCase().toCharArray();
//        char[] charsKEY = key.toUpperCase().toCharArray();
//        char[] code = new char[charsTEXT.length];
//        for (int i = 0; i < charsTEXT.length; i++) {
//            int keyAlphabet = alphabetx.indexOf(charsTEXT[i]);
//            int keyCipere = alphabetx.indexOf(charsKEY[i % key.length()]);
//            int result = (keyAlphabet - keyCipere + alphabetx.length()) % alphabetx.length();
//            code[i] = charsALPHABET[result];
//        }
//        return String.valueOf(code);
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++, j++) {
            char currentSymbol = text.charAt(i);
            if (Character.isLowerCase(currentSymbol)) {
                currentSymbol = Character.toUpperCase(currentSymbol);
            }

            if (j == VIGENERE_KEY.length() - 1) {
                j = 0;
            }

            if (!VIGENERE_ALPHABET.contains(Character.toString(currentSymbol))) {
                decryptedText.append(currentSymbol);
                continue;
            }

            int iInd = VIGENERE_ALPHABET.indexOf(currentSymbol);
            int jInd = VIGENERE_ALPHABET.indexOf(VIGENERE_KEY.charAt(j));
            int index = iInd + jInd;

            if (index >= VIGENERE_ALPHABET.length()) {
                index -= VIGENERE_ALPHABET.length();
            }

            decryptedText.append(VIGENERE_ALPHABET.charAt(index));
        }
        return decryptedText.toString();
//        return formattedText(decryptedText.toString(), false);
    }
}
