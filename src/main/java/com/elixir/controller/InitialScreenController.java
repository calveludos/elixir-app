package com.elixir.controller;

import com.elixir.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitialScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button logonButton;

    @FXML
    void loginButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
        manager.openPane("login");
    }

    @FXML
    void logonButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
        manager.openPane("logon");
    }

}
