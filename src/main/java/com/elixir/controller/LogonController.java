package com.elixir.controller;

import com.elixir.Secrets;
import com.elixir.dao.UserDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

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

            sendEmail(user);

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

            PaneManager manager = new PaneManager();
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

    private void sendEmail(User user) {
        // Configurações do servidor SMTP do Gmail
        String host = "smtp.gmail.com";
        String port = "587"; // Porta para TLS
        String username = Secrets.EMAIL; // Substitua com seu endereço de e-mail Gmail
        String password = Secrets.EMAIL_PASSWORD; // Substitua com sua senha de e-mail Gmail

        // Configurações adicionais
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Cria uma sessão de e-mail com autenticação
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Cria a mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Remetente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail())); // Destinatário
            message.setSubject("Assunto do E-mail");
            message.setText("Corpo do E-mail");

            // Envia a mensagem de e-mail
            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }

}
