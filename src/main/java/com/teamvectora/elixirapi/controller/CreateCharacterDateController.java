package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.CreateCharacterSectionController;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class CreateCharacterDateController extends CreateCharacterSectionController {

    @FXML
    private MenuButton aliagmentSelectionFiled;

    @FXML
    private TextArea apperanceField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField namePlayerField;

    @FXML
    private Label errorLabel;

    @FXML
    private Spinner<Integer> levelField;

    private CharacterMaster character;

    @FXML
    public void initialize() {
        super.initialize();

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
            if (newValue.length() > 100 || !newValue.matches("[a-zA-Z0-9 çÇáÁéÉíÍóÓúÚâÂêÊôÔãÃõÕüÜ]*")) {
                nameField.textProperty().setValue(oldValue);
            } else {
                nameField.textProperty().setValue(newValue);
            }
        });

        namePlayerField.textProperty().addListener((object, oldValue, newValue) -> {
            if (newValue.length() > 100 || !newValue.matches("[a-zA-Z0-9 çÇáÁéÉíÍóÓúÚâÂêÊôÔãÃõÕüÜ]*")) {
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

        ObjectSaveManager reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");

        try{
            assert character.getName() != null;
            nameField.setText(character.getName());
            assert character.getPlayerName() != null;
            namePlayerField.setText(character.getPlayerName());
            assert character.getAppearance() != null;
            apperanceField.setText(character.getAppearance());
            assert character.level != 0;
            levelField.getValueFactory().setValue(character.level);
            assert character.getAlignmentId() != 0;
            aliagmentSelectionFiled.setText(alignmentIdMap.get(character.getAlignmentId()));
        } catch (NullPointerException e){
            character = new CharacterMaster();
        }
    }
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

    @Override
    protected void saveCharacter(String fxml){
        try {
            character.setName(nameField.getText());
            character.level = levelField.getValue();
            character.setAppearance(apperanceField.getText());
            character.setPlayerName(namePlayerField.getText());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            errorLabel.setText("ERRO! " + e.getMessage());
            return;
        }

        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);
    }
}
