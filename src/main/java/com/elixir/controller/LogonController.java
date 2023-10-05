package com.elixir.controller;

import com.elixir.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.*;
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
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private CheckBox viewPasswordCheckbox;

    @FXML
    void loginButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
        manager.openPane("login");
    }

    @FXML
    void logonButtonAction(ActionEvent event) {
    }

    @FXML
    void viewPasswordCheckboxAction(ActionEvent event) {
        passwordField.setAccessibleRole(AccessibleRole.TEXT);
    }

    @FXML
    public void passwordFieldAction(ActionEvent event) {
    }
}
