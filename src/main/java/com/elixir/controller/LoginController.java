package com.elixir.controller;

import com.elixir.dao.UserDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Arrays;

public class LoginController {

    @FXML
    private Button createAccountButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private CheckBox viewPasswordCheckbox;

    @FXML
    private TextField textPassword;

    @FXML
    void createAccountButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager((Stage) createAccountButton.getScene().getWindow());
        manager.openPane("logon");
    }

    @FXML
    void loginButtonAction(ActionEvent event) throws SQLException {
        UserDAO userDAO = new UserDAO();
        User filter = new User(true);
        filter.setUsername(usernameField.getText());
        filter.setVerify(true);
        System.out.println(filter.getUsername());
        User user = null;
        errorLabel.setMinHeight(20.0);
        errorLabel.setMinHeight(20.0);
        errorLabel.setMinHeight(20.0);
        errorLabel.setTextFill(Color.BLACK);
        errorLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 12.0));
        errorLabel.setText("Carregando...");

        try {
            user = (User) userDAO.read(filter).values().toArray()[0];
        } catch (SQLException exception){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(exception.getMessage());
            throw new SQLException(exception);
        }
        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.saveObject("user", user);

        System.out.println(passwordField.getText());
        System.out.println(user.getPassword());
        if (BCrypt.checkpw(passwordField.getText(), user.getPassword())){
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("Inciando Seção...");

            PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
            manager.openPane("myCharactersPane");
        } else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Usuário ou Senha inválidos");
        }

        System.out.println(errorLabel.getText());

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
