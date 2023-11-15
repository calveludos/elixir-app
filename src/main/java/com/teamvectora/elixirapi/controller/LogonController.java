package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.Secrets;
import com.teamvectora.elixirapi.dao.FolderDAO;
import com.teamvectora.elixirapi.dao.UserDAO;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.Folder;
import com.teamvectora.elixirapi.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import java.sql.SQLException;

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

    private String password;

    @FXML
    void loginButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("login");
    }

    @FXML
    void logonButtonAction(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String email = emailField.getText();

        if (username != null) {
            username = username.replaceFirst("^\\s+", "").replaceFirst("\\s+$", "");
        }

        if (email != null) {
            email = email.replaceFirst("^\\s+", "").replaceFirst("\\s+$", "");
        }

        errorLabel.setMinHeight(20.0);
        errorLabel.setMinHeight(20.0);
        errorLabel.setMinHeight(20.0);
        errorLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 12.0));

        if (viewPasswordCheckbox.isSelected()){
            password = textPassword.getText();
        } else {
            password = passwordField.getText();
        }

        if (username != null && !username.isEmpty() && !username.isBlank() && !username.contains(" ") &&
                email != null && email.contains("@") && !email.contains(" ") &&
                password != null && !password.isEmpty() && !password.isBlank() && !password.contains(" ")) {

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setHashPassword(password);

            var userDAO = new UserDAO();
            var folderDAO = new FolderDAO();

            System.out.println(user.getCodeVerify());

            try {
                int id = userDAO.create(user);
                Folder folder = new Folder(id, "default");
                int folderId = folderDAO.create(folder);
                folder.setId(folderId);
                user.setId(id);
            } catch (SQLException e){
                errorLabel.setText("Nome de usuário ja existem ou este email esta sendo utilizando");
                e.printStackTrace();
                return;
            }

            ObjectSaveManager saver = new ObjectSaveManager();
            saver.saveObject("user", user);

            PaneManager manager = new PaneManager();
            manager.openPane("validationEmailPane");
        } else {
            errorLabel.setText("Preencha todos os campos corretamente");
        }
    }

    @FXML
    void viewPasswordCheckboxAction(ActionEvent event) {

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
