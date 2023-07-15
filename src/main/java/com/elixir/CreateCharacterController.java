package com.elixir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class CreateCharacterController {

    @FXML
    private Button attributesCharacterButton;

    @FXML
    private Button backgroundCharacterButton;

    @FXML
    private Button classCharacterButton;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button dateCharacterButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button raceCharacterButton;

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
        saveCharacter("createCharacterDatePane");
    }

    @FXML
    void raceCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterRacePane");
    }

    @FXML
    void attributesCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    protected abstract void saveCharacter(String fxml);
}
