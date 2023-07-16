package com.elixir;

import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    private Button backgroundCharacterButton;

    @FXML
    private MenuItem caoticoMenuItem;

    @FXML
    private Button classCharacterButton;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button dateCharacterButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField namePlayerField;

    @FXML
    private MenuItem neutroMenuItem;

    @FXML
    private Button nextDateButton;

    @FXML
    private MenuItem ordeiroMenuItem;

    @FXML
    private Button raceCharacterButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Spinner<Integer> levelField;

    @FXML
    private Button attributesCharacterButton;

    private String selectedLevel;

    private String selectedLevelId;

    private String selectedAliagment;

    private static Character character;

    private Map<Integer, String> alignmentIdMap;
    private Map<String, Integer> alignmentMap;

    @FXML
    private void initialize() {

        ordeiroMenuItem.setOnAction(this::aligmentMenuItemClicked);
        neutroMenuItem.setOnAction(this::aligmentMenuItemClicked);
        caoticoMenuItem.setOnAction(this::aligmentMenuItemClicked);

        alignmentIdMap = new HashMap<>();
        alignmentMap = new HashMap<>();

        alignmentIdMap.put(1, "Ordeiro");
        alignmentIdMap.put(2, "Neutro");
        alignmentIdMap.put(3, "Caótico");

        alignmentMap.put("Ordeiro", 1);
        alignmentMap.put("Neutro", 2);
        alignmentMap.put("Caótico", 3);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelField.setValueFactory(valueFactory);
        levelField.setEditable(true);

        levelField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                levelField.getEditor().setText(oldValue);
            } else {
                int value = Integer.parseInt(newValue);
                if (value < 1 || value > 20) {
                    levelField.getEditor().setText(oldValue);
                }
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
    void dateCharacterButtonAction(ActionEvent event) {
        nameField.setText(character.getName());
        apperanceField.setText(character.getAppearance());
        levelField.setPromptText(String.valueOf(character.getExperience()));
        selectedAliagment = alignmentIdMap.get(character.getAlignmentId());
        saveCharacter("createCharacterAttributesPane");
    }

    @FXML
    void nextDateButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    private void aligmentMenuItemClicked(ActionEvent event){
        MenuItem menuItem = (MenuItem) event.getSource();
        selectedAliagment = menuItem.getText();
        aliagmentSelectionFiled.setText(String.valueOf(selectedAliagment));
    }

    private void saveCharacter(String fxml){
        Character character = new Character();
        try {
            character.setName(nameField.getText());
            character.setExperience(levelField.getValue());
            character.setAlignmentId(alignmentMap.get(selectedAliagment));
            character.setAppearance(apperanceField.getText());

        } catch (IllegalArgumentException e){
            errorLabel.setText("ERRO! " + e.getMessage());
            return;
        }

        this.character = character;

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }
    public static Character getCharacter() {
        return character;
    }
}
