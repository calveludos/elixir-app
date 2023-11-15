package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.CreateCharacterSectionController;

import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.Attribute;
import com.teamvectora.elixirapi.model.CharacterMaster;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.converter.IntegerStringConverter;


public class CreateCharacterAttributesController extends CreateCharacterSectionController {

    public Label strBonusLabel;
    public Label dexBonusLabel;
    public Label conBonusLabel;
    public Label intBonusLabel;
    public Label wisBonusLabel;
    public Label chaBonusLabel;
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
        Attribute attributeBonus = (Attribute) reader.getObject("bonus");
        attribute = character.getAttribute();
        reader.printMap();

        strField.setText(String.valueOf(attribute.getStrength()));
        dexField.setText(String.valueOf(attribute.getDexterity()));
        conField.setText(String.valueOf(attribute.getConstitution()));
        intField.setText(String.valueOf(attribute.getIntelligence()));
        wisField.setText(String.valueOf(attribute.getWisdom()));
        chaField.setText(String.valueOf(attribute.getCharisma()));


        if (attributeBonus != null) {
            strBonusLabel.setText(attributeBonus.getStrength() == 0 ? "" : String.valueOf(attributeBonus.getStrength()));
            dexBonusLabel.setText(attributeBonus.getDexterity() == 0 ? "" : String.valueOf(attributeBonus.getDexterity()));
            conBonusLabel.setText(attributeBonus.getConstitution() == 0 ? "" : String.valueOf(attributeBonus.getConstitution()));
            intBonusLabel.setText(attributeBonus.getIntelligence() == 0 ? "" : String.valueOf(attributeBonus.getIntelligence()));
            wisBonusLabel.setText(attributeBonus.getWisdom() == 0 ? "" : String.valueOf(attributeBonus.getWisdom()));
            chaBonusLabel.setText(attributeBonus.getCharisma() == 0 ? "" : String.valueOf(attributeBonus.getCharisma()));

            strBonusLabel.setTextFill(!strBonusLabel.getText().isEmpty() && Double.parseDouble(strBonusLabel.getText()) > 0 ? Color.GREEN : Color.RED );
            dexBonusLabel.setTextFill(!dexBonusLabel.getText().isEmpty() && Double.parseDouble(dexBonusLabel.getText()) > 0 ? Color.GREEN : Color.RED );
            conBonusLabel.setTextFill(!conBonusLabel.getText().isEmpty() && Double.parseDouble(conBonusLabel.getText()) > 0 ? Color.GREEN : Color.RED );
            intBonusLabel.setTextFill(!intBonusLabel.getText().isEmpty() && Double.parseDouble(intBonusLabel.getText()) > 0 ? Color.GREEN : Color.RED );
            wisBonusLabel.setTextFill(!wisBonusLabel.getText().isEmpty() && Double.parseDouble(wisBonusLabel.getText()) > 0 ? Color.GREEN : Color.RED );
            chaBonusLabel.setTextFill(!chaBonusLabel.getText().isEmpty() && Double.parseDouble(chaBonusLabel.getText()) > 0 ? Color.GREEN : Color.RED );

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
