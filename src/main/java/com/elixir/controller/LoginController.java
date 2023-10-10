package com.elixir.controller;

import com.elixir.controller.objects.ValidationButton;
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
import javafx.scene.layout.VBox;
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
    private VBox vboxBody;

    private String password;

    @FXML
    void createAccountButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager((Stage) createAccountButton.getScene().getWindow());
        manager.openPane("logon");
    }

    @FXML
void loginButtonAction(ActionEvent event) throws SQLException {
    String username = usernameField.getText();
    String password = passwordField.getText();
    UserDAO userDAO = new UserDAO();
    User filter = new User(true);
    filter.setUsername(username);
    filter.setVerify(true);
    System.out.println(filter.getUsername());
    User user = null;
    errorLabel.setMinHeight(20.0);
    errorLabel.setMinHeight(20.0);
    errorLabel.setMinHeight(20.0);
    errorLabel.setTextFill(Color.BLACK);
    errorLabel.setFont(Font.font("System Bold", FontWeight.BOLD, 12.0));
    
    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,12}$";
    if (!password.matches(regex)) {
        errorLabel.setText("Senha inválida. A senha deve ter entre 6 e 12 caracteres, incluindo pelo menos uma letra maiúscula, uma letra minúscula e um número.");
        return;
    }
    
    user = userDAO.getUserByUsernameAndPassword(username, password); 
    
    if (user == null) {
        errorLabel.setText("Nome de usuário ou senha incorretos.");
        return;
    }
    
    errorLabel.setText("Carregando...");

   
}

        try {
            user = (User) userDAO.read(filter).values().toArray()[0];
        } catch (SQLException exception){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(exception.getMessage());
            throw new SQLException(exception);
        } catch (java.lang.ArrayIndexOutOfBoundsException exception){
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Usuário ainda não confirmado");
            vboxBody.getChildren().add(new ValidationButton("Confimação"));
            return;
        }

        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.saveObject("user", user);

        if (viewPasswordCheckbox.isSelected()){
            password = textPassword.getText();
        } else {
            password = passwordField.getText();
        }

        System.out.println(password);
        System.out.println(user.getPassword());

        if (BCrypt.checkpw(password, user.getPassword())){
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
