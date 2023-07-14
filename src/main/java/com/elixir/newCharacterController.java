package com.elixir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class newCharacterController {

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button createCharacterMenuButton;

    @FXML
    void createCharacterButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("createCharacterDatePane");
    }

}
