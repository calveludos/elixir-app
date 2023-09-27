package com.elixir.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.elixir.manager.*;

public class newCharacterController extends MenuController {

    @FXML
    private Button createCharacterButton;

    @FXML
    void createCharacterButtonAction(ActionEvent event) {
        ObjectSaveManager objectSaveManager = new ObjectSaveManager();
        objectSaveManager.cleanObjects();

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("createCharacterDatePane");
    }

}
