package com.elixir.controller;

import com.elixir.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogonController {

    @FXML
    private TextField emailField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button logonButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void loginButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
        manager.openPane("login");
    }

    @FXML
    void logonButtonAction(ActionEvent event) {
    }

}
