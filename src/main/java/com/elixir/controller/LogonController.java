package com.elixir.controller;

import com.elixir.Secrets;
import com.elixir.dao.UserDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.SQLException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

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
        PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
        manager.openPane("login");
    }

    @FXML
    void logonButtonAction(ActionEvent event) throws SQLException {
        String username = usernameField.getText();
        String email = emailField.getText();

        errorLabel.setMinHeight(20.0);
        errorLabel.setMinHeight(20.0);
        errorLabel.setMinHeight(20.0);
        errorLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 12.0));

        if (viewPasswordCheckbox.isSelected()){
            password = textPassword.getText();
        } else {
            password = passwordField.getText();
        }

        if (username != null && !username.isEmpty() &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty()) {

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setHashPassword(password);


            var userDAO = new UserDAO();
            System.out.println(user.getCodeVerify());
            //sendEmail(user);
            try {
                int id = userDAO.create(user);
                user.setId(id);
            } catch (SQLException e){
                errorLabel.setText("Nome de usuário ja existem ou este email esta sendo utilizando");
                e.printStackTrace();
                return;
            }

            ObjectSaveManager saver = new ObjectSaveManager();
            saver.saveObject("user", user);

            PaneManager manager = new PaneManager((Stage) loginButton.getScene().getWindow());
            manager.openPane("validationEmailPane");

        } else {
            errorLabel.setText("Preencha todos os campos");
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

    private void sendEmail(User user){
        String myEmail = Secrets.EMAIL;
        String myPassword = Secrets.EMAIL_PASSWORD;

        SimpleEmail gmail = new SimpleEmail();
        gmail.setHostName("smtp.gmail.com");
        gmail.setSmtpPort(465);
        gmail.setAuthenticator(new DefaultAuthenticator(myEmail, myPassword));
        gmail.setSSLOnConnect(true);

        try {
            gmail.setFrom(myEmail);
            gmail.setSubject("Confirmação de Email - Elixr Maker");
            gmail.setMsg("Aqui está o seu código: " + user.getCodeVerify());
            gmail.addTo(user.getEmail());
            gmail.send();
            System.out.println("Email foi enviado com sucesso!");

            errorLabel.setText("Usuário Cadastrado, confirme seu email. Carregando...");

        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

}
