package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.elixir.manager.*;

public class NewCharacterController extends MenuController {

    @FXML
    private Button createNewCharacterButton;

    @FXML
    void createNewCharacterButtonAction(ActionEvent event) {
        ObjectSaveManager objectSaveManager = new ObjectSaveManager();
        objectSaveManager.cleanObjects();

        PaneManager paneManager = new PaneManager((Stage) createNewCharacterButton.getScene().getWindow());
        paneManager.openPane("createCharacterDatePane");
    }

}
