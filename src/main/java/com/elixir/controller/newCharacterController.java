package com.elixir.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.elixir.manager.*;

public class newCharacterController {

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button createCharacterMenuButton;

    @FXML
    void createCharacterButtonAction(ActionEvent event) {
        ObjectSaveManager<Object> objectSaveManager = new ObjectSaveManager<>();
        objectSaveManager.cleanObjects();

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("createCharacterDatePane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

}
