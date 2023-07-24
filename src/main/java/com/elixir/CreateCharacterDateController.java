package com.elixir;

import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class CreateCharacterDateController {

    @FXML
    private MenuButton aliagmentSelectionFiled;

    @FXML
    private TextArea apperanceField;

    @FXML
    private Button createCharacterButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField namePlayerField;

    @FXML
    private Label errorLabel;

    @FXML
    private Spinner<Integer> levelField;

    private Character character;

    @FXML
    private void initialize() {

        Map<Integer, String> alignmentIdMap = new HashMap<>();

        alignmentIdMap.put(1, "Ordeiro");
        alignmentIdMap.put(2, "Neutro");
        alignmentIdMap.put(3, "Caótico");

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelField.setValueFactory(valueFactory);
        levelField.setEditable(true);

        levelField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                levelField.getEditor().setText(oldValue);
            } else {
                int value = 1;
                try {
                    value = Integer.parseInt(newValue);
                } catch (Exception ignored) {}
                if (value < 1 || value > 20) {
                    levelField.getEditor().setText(oldValue);
                }
            }
        });

        nameField.textProperty().addListener((object, oldValue, newValue) -> {
            if (newValue.length() > 100 || !newValue.matches("[a-zA-Z0-9 ]*")) {
                nameField.textProperty().setValue(oldValue);
            } else {
                nameField.textProperty().setValue(newValue);
            }
        });

        namePlayerField.textProperty().addListener((object, oldValue, newValue) -> {
            if (newValue.length() > 100 || !newValue.matches("[a-zA-Z0-9 ]*")) {
                namePlayerField.textProperty().setValue(oldValue);
            } else {
                namePlayerField.textProperty().setValue(newValue);
            }
        });

        apperanceField.textProperty().addListener((object, oldValue, newValue) -> {
            if (newValue.length() > 300) {
                apperanceField.textProperty().setValue(oldValue);
            } else {
                apperanceField.textProperty().setValue(newValue);
            }
        });

        NumberFormat format = new DecimalFormat("0");
        StringConverter<Integer> converter = new StringConverter<>() {
            @Override
            public String toString(Integer value) {
                if (value != null) {
                    return format.format(value);
                } else {
                    return "";
                }
            }

            @Override
            public Integer fromString(String text) {
                try {
                    return format.parse(text).intValue();
                } catch (Exception e) {
                    return null;
                }
            }
        };

        levelField.getValueFactory().setConverter(converter);

        ObjectSaveManager reader = new ObjectSaveManager<>();
        character = (Character) reader.getObject("character");

        try{
            if (!character.isDateNull()){
                nameField.setText(character.getName());
                namePlayerField.setText(character.getPlayerName());
                apperanceField.setText(character.getAppearance());
                levelField.getValueFactory().setValue(character.getExperience());
                aliagmentSelectionFiled.setText(alignmentIdMap.get(character.getAlignmentId()));
            }
        } catch (java.lang.NullPointerException e){
            character = new Character();
        }
    }

    @FXML
    void backgroundCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterBackgroundPane");
    }

    @FXML
    void classCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterClassPane");
    }

    @FXML
    void createCharacterButtonAction(ActionEvent event) {
        saveCharacter("newCharacterPane");
    }

    @FXML
    void raceCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterRacePane");
    }

    @FXML
    void attributesCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

    @FXML
    void nextDateButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    @FXML
    public void dateCharacterButtonAction(ActionEvent event) {}

    @FXML
    void ordeiroSelected(ActionEvent event){
        aliagmentSelectionFiled.setText("Ordeiro");
        character.setAlignmentId(1);
    }

    @FXML
    void caoticoSelected(ActionEvent event){
        aliagmentSelectionFiled.setText("Caótico");
        character.setAlignmentId(3);
    }

    @FXML
    void neutroSelected(ActionEvent event){
        aliagmentSelectionFiled.setText("Neutro");
        character.setAlignmentId(2);
    }

    private void saveCharacter(String fxml){
        try {
            character.setName(nameField.getText());
            character.setExperience(levelField.getValue());
            character.setAppearance(apperanceField.getText());
            character.setPlayerName(namePlayerField.getText());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            errorLabel.setText("ERRO! " + e.getMessage());
            return;
        }

        ObjectSaveManager<Character> saver = new ObjectSaveManager<>();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character){
        this.character = character;
    }
}
