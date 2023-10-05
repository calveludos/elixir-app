package com.elixir.controller;

import com.elixir.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Scanner;

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
    private TextField textPassword;

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

        String password;
        if (viewPasswordCheckbox.isSelected()){
            password = passwordField.getText();
            textPassword.setText(password);
            textPassword.setMinHeight(39.0);
            passwordField.setMinHeight(0);
        } else {
            password = textPassword.getText();
            passwordField.setText(password);
            passwordField.setMinHeight(39.0);
            textPassword.setMinHeight(0);
        }
    }

}
