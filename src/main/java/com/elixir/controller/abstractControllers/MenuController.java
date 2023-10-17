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

    @FXML
    private Button diceMenuButtonAction;

    public void createNewCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    void myCharactersMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("myCharactersPane");
    }

    @FXML
    void startPaneMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("startScreenPane");
    }

    @FXML
    void diceMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("rollDicePane");
    }
}
