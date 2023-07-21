package com.elixir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface CharacterCreation {
    @FXML
    void raceCharacterButtonAction(ActionEvent event);
    @FXML
    void classCharacterButtonAction(ActionEvent event);
    @FXML
    void attributesCharacterButtonAction(ActionEvent event);
    @FXML
    void dateCharacterButtonAction(ActionEvent event);
    @FXML
    void backgroundCharacterButtonAction(ActionEvent event);
    @FXML
    void createCharacterButtonAction(ActionEvent event);
    @FXML
    void nextStepButtonAction(ActionEvent event);
    void saveCharacter(String fxml);
}
