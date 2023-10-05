package com.elixir.controller.abstractControllers;

import com.elixir.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button createNewCharacterMenuButton;

    @FXML
    private Button myCharactersMenuButton;

    @FXML
    private Button startPaneMenuButton;

    public void createNewCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createNewCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    void myCharactersMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharactersMenuButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

    @FXML
    void startPaneMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) startPaneMenuButton.getScene().getWindow());
        paneManager.openPane("NormalStartScreen");
    }
}
