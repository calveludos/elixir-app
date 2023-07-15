package com.elixir;

import com.elixir.dao.AttributeDAO;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
    private Button nextDateButton;

    @FXML
    private Button raceCharacterButton;

    @FXML
    private Pane sectionsPane;

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
    private Button nextAttribute;

    @FXML
    private Button createCharacterButton;

    private static Character character;

    @FXML
    private void initialize() {
        applyIntegerFormatter(chaField);
        applyIntegerFormatter(conField);
        applyIntegerFormatter(dexField);
        applyIntegerFormatter(strField);
        applyIntegerFormatter(intField);
        applyIntegerFormatter(wisField);

        strField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int forFieldInt = Integer.parseInt(strField.getText());
            int modifier = (forFieldInt % 2 == 0) ? (forFieldInt - 10) / 2 : (forFieldInt - 11) / 2;
            if (forFieldInt < 10) {
                strLabel.setText(String.valueOf(modifier));
            } else if (forFieldInt < 30) {
                strLabel.setText("+" + modifier);
            } else {
                strLabel.setFont(new Font(21));
                strLabel.setText("+10");
                strField.setText("30");
            }
        });

        chaField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int chaFieldInt = Integer.parseInt(chaField.getText());
            int modifier = (chaFieldInt % 2 == 0) ? (chaFieldInt - 10) / 2 : (chaFieldInt - 11) / 2;
            if (chaFieldInt < 10) {
                chaLabel.setText(String.valueOf(modifier));
            } else if (chaFieldInt < 30) {
                chaLabel.setText("+" + modifier);
            } else {
                chaLabel.setFont(new Font(21));
                chaLabel.setText("+10");
                chaField.setText("30");
            }
        });

        conField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int conFieldInt = Integer.parseInt(conField.getText());
            int modifier = (conFieldInt % 2 == 0) ? (conFieldInt - 10) / 2 : (conFieldInt - 11) / 2;
            if (conFieldInt < 10) {
                conLabel.setText(String.valueOf(modifier));
            } else if (conFieldInt < 30) {
                conLabel.setText("+" + modifier);
            } else {
                conLabel.setFont(new Font(21));
                conLabel.setText("+10");
                conField.setText("30");
            }
        });

        dexField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int dexFieldInt = Integer.parseInt(dexField.getText());
            int modifier = (dexFieldInt % 2 == 0) ? (dexFieldInt - 10) / 2 : (dexFieldInt - 11) / 2;
            if (dexFieldInt < 10) {
                dexLabel.setText(String.valueOf(modifier));
            } else if (dexFieldInt < 30) {
                dexLabel.setText("+" + modifier);
            } else {
                dexLabel.setFont(new Font(21));
                dexLabel.setText("+10");
                dexField.setText("30");
            }
        });

        intField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int intFieldInt = Integer.parseInt(intField.getText());
            int modifier = (intFieldInt % 2 == 0) ? (intFieldInt - 10) / 2 : (intFieldInt - 11) / 2;
            if (intFieldInt < 10) {
                intLabel.setText(String.valueOf(modifier));
            } else if (intFieldInt < 30) {
                intLabel.setText("+" + modifier);
            } else {
                intLabel.setFont(new Font(21));
                intLabel.setText("+10");
                intField.setText("30");
            }
        });

        wisField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            int wisFieldInt = Integer.parseInt(wisField.getText());
            int modifier = (wisFieldInt % 2 == 0) ? (wisFieldInt - 10) / 2 : (wisFieldInt - 11) / 2;
            if (wisFieldInt < 10) {
                wisLabel.setText(String.valueOf(modifier));
            } else if (wisFieldInt < 30) {
                wisLabel.setText("+" + modifier);
            } else {
                wisLabel.setFont(new Font(21));
                wisLabel.setText("+10");
                wisField.setText("30");
            }
        });

        character = CreateCharacterDateController.getCharacter();

    }

    private void applyIntegerFormatter(TextField textField) {
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 0,
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
    void dateCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
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
    void nextAttributesButtonAction(ActionEvent event) {
        saveCharacter("createCharacterRacePane");
    }
    @FXML
    void conFieldAction(ActionEvent event) {
        int conFieldInt = Integer.parseInt(conField.getText());
        conField.setText(String.valueOf((conFieldInt % 2 == 0) ? (conFieldInt - 10) / 2 : (conFieldInt - 11) / 2));
    }

    @FXML
    void dexFieldAction(ActionEvent event) {
        int dexFieldInt = Integer.parseInt(dexField.getText());
        dexField.setText(String.valueOf((dexFieldInt % 2 == 0) ? (dexFieldInt - 10) / 2 : (dexFieldInt - 11) / 2));
    }

    @FXML
    void forFieldAction(ActionEvent event) {
        int forFieldInt = Integer.parseInt(strField.getText());
        strField.setText(String.valueOf((forFieldInt % 2 == 0) ? (forFieldInt - 10) / 2 : (forFieldInt - 11) / 2));
    }

    @FXML
    void intFieldAction(ActionEvent event) {
        int intFieldInt = Integer.parseInt(intField.getText());
        intField.setText(String.valueOf((intFieldInt % 2 == 0) ? (intFieldInt - 10) / 2 : (intFieldInt - 11) / 2));
    }

    @FXML
    void wisFieldAction(ActionEvent event) {
        int wisFieldInt = Integer.parseInt(wisField.getText());
        wisField.setText(String.valueOf((wisFieldInt % 2 == 0) ? (wisFieldInt - 10) / 2 : (wisFieldInt - 11) / 2));
    }

    private void saveCharacter(String fxml){
        try {
            Attribute attribute = new Attribute(
                    Integer.parseInt(strField.getText()),
                    Integer.parseInt(wisField.getText()),
                    Integer.parseInt(dexField.getText()),
                    Integer.parseInt(intField.getText()),
                    Integer.parseInt(conField.getText()),
                    Integer.parseInt(chaField.getText()));

            AttributeDAO attributeDAO = new AttributeDAO();
            int id = attributeDAO.create(attribute);

            character.setAttributeId(id);

        } catch (IllegalArgumentException e){
            errorLabel.setText("ERRO! " + e.getMessage());
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(character.toString());

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }

    public static Character getCharacter() {
        return character;
    }
}
