package com.elixir;

import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

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
    private MenuItem level1;

    @FXML
    private MenuItem level10;

    @FXML
    private MenuItem level11;

    @FXML
    private MenuItem level12;

    @FXML
    private MenuItem level13;

    @FXML
    private MenuItem level14;

    @FXML
    private MenuItem level15;

    @FXML
    private MenuItem level16;

    @FXML
    private MenuItem level17;

    @FXML
    private MenuItem level18;

    @FXML
    private MenuItem level19;

    @FXML
    private MenuItem level2;

    @FXML
    private MenuItem level20;

    @FXML
    private MenuItem level3;

    @FXML
    private MenuItem level4;

    @FXML
    private MenuItem level5;

    @FXML
    private MenuItem level6;

    @FXML
    private MenuItem level7;

    @FXML
    private MenuItem level8;

    @FXML
    private MenuItem level9;

    @FXML
    private MenuButton levelSelectionField;

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
    private String selectedLevel;
    private String selectedLevelId;
    private String selectedAliagment;
    @FXML
    private void initialize() {
        for (int i = 1; i <= 20; i++) {
            getLevelMenuItem(i).setOnAction(this::levelMenuItemClicked);
        }
        ordeiroMenuItem.setOnAction(this::aligmentMenuItemClicked);
        neutroMenuItem.setOnAction(this::aligmentMenuItemClicked);
        caoticoMenuItem.setOnAction(this::aligmentMenuItemClicked);
    }

    private MenuItem getLevelMenuItem(int level) {
        switch (level) {
            case 1:
                return level1;
            case 2:
                return level2;
            case 3:
                return level3;
            case 4:
                return level4;
            case 5:
                return level5;
            case 6:
                return level6;
            case 7:
                return level7;
            case 8:
                return level8;
            case 9:
                return level9;
            case 10:
                return level10;
            case 11:
                return level11;
            case 12:
                return level12;
            case 13:
                return level13;
            case 14:
                return level14;
            case 15:
                return level15;
            case 16:
                return level16;
            case 17:
                return level17;
            case 18:
                return level18;
            case 19:
                return level19;
            case 20:
                return level20;
            default:
                return null;
        }
    }

    private void levelMenuItemClicked(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        selectedLevel = menuItem.getText();
        selectedLevelId = menuItem.getId();
        levelSelectionField.setText(selectedLevel);
    }
    private void aligmentMenuItemClicked(ActionEvent event){
        MenuItem menuItem = (MenuItem) event.getSource();
        selectedAliagment = menuItem.getText();
        aliagmentSelectionFiled.setText(String.valueOf(selectedAliagment));
    }

    private void saveCharacter(){
        Character character = new Character();
        try {
            character.setName(nameField.getText());
            character.setExperience(Integer.parseInt(selectedLevel));
            character.setAlignmentId(getAlignment(selectedAliagment));
            character.setAppearance(apperanceField.getText());
        } catch (IllegalArgumentException e){
            errorLabel.setText("ERRO! " + e.getMessage());
        }
    }

    private int getAlignment(String aliagment){
        if (aliagment == "Ordeiro") {
            return 1;
        } else if (aliagment == "Caótico") {
            return 3;
        } else if (aliagment == "Neutro") {
            return 2;
        }
        return 0;
    }

    @FXML
    void backgroundCharacterButtonAction(ActionEvent event) {
        saveCharacter();
    }

    @FXML
    void classCharacterButtonAction(ActionEvent event) {
        saveCharacter();
    }

    @FXML
    void createCharacterButtonAction(ActionEvent event) {
        saveCharacter();
    }

    @FXML
    void dateCharacterButtonAction(ActionEvent event) {
        saveCharacter();
    }

    @FXML
    void nextDateButtonAction(ActionEvent event) {
        saveCharacter();
    }

    @FXML
    void raceCharacterButtonAction(ActionEvent event) {
        saveCharacter();
    }

}
