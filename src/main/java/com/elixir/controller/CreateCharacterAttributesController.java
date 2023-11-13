package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;

import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.manager.Tuple;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import com.elixir.model.CharacterMaster;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.List;


public class CreateCharacterAttributesController extends CreateCharacterSectionController {

    @FXML
    private TextField chaField;

    @FXML
    private TextField conField;

    @FXML
    private TextField dexField;

    @FXML
    private TextField intField;

    @FXML
    private TextField strField;

    @FXML
    private TextField wisField;

    @FXML
    private Label errorLabel;

    private CharacterMaster character;
    private Attribute attribute;

    @FXML
    public void initialize() {
        super.initialize();

        applyIntegerFormatter(chaField);
        applyIntegerFormatter(conField);
        applyIntegerFormatter(dexField);
        applyIntegerFormatter(strField);
        applyIntegerFormatter(intField);
        applyIntegerFormatter(wisField);

        strField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int forFieldInt = Integer.parseInt(strField.getText());
            if (forFieldInt < 1){
                strField.setText("1");
            } else if (forFieldInt > 30) {
                strField.setText("29");
            }
        });

        chaField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int chaFieldInt = Integer.parseInt(chaField.getText());
            if (chaFieldInt < 1){
                chaField.setText("1");
            } else if (chaFieldInt > 30) {
                chaField.setText("29");
            }
        });

        conField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int conFieldInt = Integer.parseInt(conField.getText());
            if (conFieldInt < 1){
                conField.setText("1");
            } else if (conFieldInt > 30) {
                conField.setText("29");
            }
        });

        dexField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int dexFieldInt = Integer.parseInt(dexField.getText());
            if (dexFieldInt < 1){
                dexField.setText("1");
            } else if (dexFieldInt > 30) {
                dexField.setText("29");
            }
        });

        intField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int intFieldInt = Integer.parseInt(intField.getText());
            if (intFieldInt < 1){
                intField.setText("1");
            } else if (intFieldInt > 30) {
                intField.setText("29");
            }
        });

        wisField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int wisFieldInt = Integer.parseInt(wisField.getText());
            if (wisFieldInt < 1){
                wisField.setText("1");
            } else if (wisFieldInt > 30) {
                wisField.setText("29");
            }
        });

        ObjectSaveManager reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");
        attribute = character.getAttribute();
        reader.printMap();

        if (character == null){
            character = new CharacterMaster();
        }

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

    @Override
    protected void saveCharacter(String fxml){
        attribute.setStrength(Integer.parseInt(strField.getText()));
        attribute.setWisdom(Integer.parseInt(wisField.getText()));
        attribute.setDexterity(Integer.parseInt(dexField.getText()));
        attribute.setIntelligence(Integer.parseInt(intField.getText()));
        attribute.setConstitution(Integer.parseInt(conField.getText()));
        attribute.setCharisma(Integer.parseInt(chaField.getText()));

        character.setAttribute(attribute);

        ObjectSaveManager saverCharacter = new ObjectSaveManager();
        saverCharacter.saveObject("character", character);

        System.out.println(character.toString());

        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);

    }


}
