package com.elixir;

import com.elixir.dao.AttributeDAO;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;

public class CreateCharacterAttributesController {
    @FXML
    private TextField chaField;

    @FXML
    private Label chaLabel;

    @FXML
    private TextField conField;

    @FXML
    private Label conLabel;

    @FXML
    private TextField dexField;

    @FXML
    private Label dexLabel;

    @FXML
    private TextField intField;

    @FXML
    private Label intLabel;

    @FXML
    private TextField strField;

    @FXML
    private Label strLabel;

    @FXML
    private TextField wisField;

    @FXML
    private Label wisLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Button createCharacterButton;

    private Character character;
    private Attribute attribute;

    @FXML
    private void initialize() throws SQLException {
        applyIntegerFormatter(chaField);
        applyIntegerFormatter(conField);
        applyIntegerFormatter(dexField);
        applyIntegerFormatter(strField);
        applyIntegerFormatter(intField);
        applyIntegerFormatter(wisField);

        strField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int forFieldInt = Integer.parseInt(strField.getText());
            int modifier = (forFieldInt % 2 == 0) ? (forFieldInt - 10) / 2 : (forFieldInt - 11) / 2;
            if (forFieldInt < 1){
                strLabel.setText("-5");
                strField.setText("1");
            } else if (forFieldInt < 10) {
                strLabel.setText(String.valueOf(modifier));
            } else if (forFieldInt < 30) {
                strLabel.setText("+" + modifier);
            } else {
                strLabel.setFont(new Font(21));
                strLabel.setText("+9");
                strField.setText("29");
            }
        });

        chaField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int chaFieldInt = Integer.parseInt(chaField.getText());
            int modifier = (chaFieldInt % 2 == 0) ? (chaFieldInt - 10) / 2 : (chaFieldInt - 11) / 2;
            if (chaFieldInt < 1){
                chaLabel.setText("-5");
                chaField.setText("1");
            } else if (chaFieldInt < 10) {
                chaLabel.setText(String.valueOf(modifier));
            } else if (chaFieldInt < 30) {
                chaLabel.setText("+" + modifier);
            } else {
                chaLabel.setFont(new Font(21));
                chaLabel.setText("+9");
                chaField.setText("29");
            }
        });

        conField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int conFieldInt = Integer.parseInt(conField.getText());
            int modifier = (conFieldInt % 2 == 0) ? (conFieldInt - 10) / 2 : (conFieldInt - 11) / 2;
            if (conFieldInt < 1){
                conLabel.setText("-5");
                conField.setText("1");
            } else if (conFieldInt < 10) {
                conLabel.setText(String.valueOf(modifier));
            } else if (conFieldInt < 30) {
                conLabel.setText("+" + modifier);
            } else {
                conLabel.setFont(new Font(21));
                conLabel.setText("+9");
                conField.setText("29");
            }
        });

        dexField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int dexFieldInt = Integer.parseInt(dexField.getText());
            int modifier = (dexFieldInt % 2 == 0) ? (dexFieldInt - 10) / 2 : (dexFieldInt - 11) / 2;
            if (dexFieldInt < 1){
                dexLabel.setText("-5");
                dexField.setText("1");
            } else if (dexFieldInt < 10) {
                dexLabel.setText(String.valueOf(modifier));
            } else if (dexFieldInt < 30) {
                dexLabel.setText("+" + modifier);
            } else {
                dexLabel.setFont(new Font(21));
                dexLabel.setText("+9");
                dexField.setText("29");
            }
        });

        intField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int intFieldInt = Integer.parseInt(intField.getText());
            int modifier = (intFieldInt % 2 == 0) ? (intFieldInt - 10) / 2 : (intFieldInt - 11) / 2;
            if (intFieldInt < 1){
                intLabel.setText("-5");
                intField.setText("1");
            } else if (intFieldInt < 10) {
                intLabel.setText(String.valueOf(modifier));
            } else if (intFieldInt < 30) {
                intLabel.setText("+" + modifier);
            } else {
                intLabel.setFont(new Font(21));
                intLabel.setText("+9");
                intField.setText("29");
            }
        });

        wisField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int wisFieldInt = Integer.parseInt(wisField.getText());
            int modifier = (wisFieldInt % 2 == 0) ? (wisFieldInt - 10) / 2 : (wisFieldInt - 11) / 2;
            if (wisFieldInt < 1){
                wisLabel.setText("-5");
                wisField.setText("1");
            } else if (wisFieldInt < 10) {
                wisLabel.setText(String.valueOf(modifier));
            } else if (wisFieldInt < 30) {
                wisLabel.setText("+" + modifier);
            } else {
                wisLabel.setFont(new Font(21));
                wisLabel.setText("+9");
                wisField.setText("29");
            }
        });

        ObjectSaveManager reader = new ObjectSaveManager<>();
        character = (Character) reader.getObject("character");
        attribute = (Attribute) reader.getObject("attribute");

        if (attribute == null) {
            attribute = new Attribute();
        } else {
            strField.setText(String.valueOf(attribute.getStrength()));
            dexField.setText(String.valueOf(attribute.getDexterity()));
            conField.setText(String.valueOf(attribute.getConstitution()));
            intField.setText(String.valueOf(attribute.getIntelligence()));
            wisField.setText(String.valueOf(attribute.getWisdom()));
            chaField.setText(String.valueOf(attribute.getCharisma()));
        }

        Platform.runLater(() -> dexField.requestFocus());
        Platform.runLater(() -> conField.requestFocus());
        Platform.runLater(() -> intField.requestFocus());
        Platform.runLater(() -> wisField.requestFocus());
        Platform.runLater(() -> chaField.requestFocus());
        Platform.runLater(() -> strField.requestFocus());

        if (character == null){
            character = new Character();
        }
    }
    private void applyIntegerFormatter(TextField textField) {
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 10,
                change -> {
                    String newText = change.getControlNewText();
                    if (newText.matches("-?\\d*")) {
                        return change;
                    }
                    return null;
                });
        textField.setTextFormatter(textFormatter);
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
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
    void dateCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterDatePane");
    }

    @FXML
    void nextAttributesButtonAction(ActionEvent event) {
        saveCharacter("createCharacterRacePane");
    }

    @FXML
    public void attributesCharacterButtonAction(ActionEvent event) {}

    @FXML
    void conFieldAction(ActionEvent event) {
        int conFieldInt = Integer.parseInt(conField.getText());
        conLabel.setText(String.valueOf((conFieldInt % 2 == 0) ? (conFieldInt - 10) / 2 : (conFieldInt - 11) / 2));
    }

    @FXML
    void dexFieldAction(ActionEvent event) {
        int dexFieldInt = Integer.parseInt(dexField.getText());
        dexLabel.setText(String.valueOf((dexFieldInt % 2 == 0) ? (dexFieldInt - 10) / 2 : (dexFieldInt - 11) / 2));
    }

    @FXML
    void forFieldAction(ActionEvent event) {
        int forFieldInt = Integer.parseInt(strField.getText());
        strLabel.setText(String.valueOf((forFieldInt % 2 == 0) ? (forFieldInt - 10) / 2 : (forFieldInt - 11) / 2));
    }

    @FXML
    void intFieldAction(ActionEvent event) {
        int intFieldInt = Integer.parseInt(intField.getText());
        intLabel.setText(String.valueOf((intFieldInt % 2 == 0) ? (intFieldInt - 10) / 2 : (intFieldInt - 11) / 2));
    }

    @FXML
    void wisFieldAction(ActionEvent event) {
        int wisFieldInt = Integer.parseInt(wisField.getText());
        wisLabel.setText(String.valueOf((wisFieldInt % 2 == 0) ? (wisFieldInt - 10) / 2 : (wisFieldInt - 11) / 2));
    }

    private void saveCharacter(String fxml){

        attribute.setStrength(Integer.parseInt(strField.getText()));
        attribute.setWisdom(Integer.parseInt(wisField.getText()));
        attribute.setDexterity(Integer.parseInt(dexField.getText()));
        attribute.setIntelligence(Integer.parseInt(intField.getText()));
        attribute.setConstitution(Integer.parseInt(conField.getText()));
        attribute.setCharisma(Integer.parseInt(chaField.getText()));

        ObjectSaveManager<Attribute> saver = new ObjectSaveManager<>();
        saver.saveObject("attribute", attribute);

        ObjectSaveManager<Character> saverCharacter = new ObjectSaveManager<>();
        saverCharacter.saveObject("character", character);

        System.out.println(character.toString());

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
