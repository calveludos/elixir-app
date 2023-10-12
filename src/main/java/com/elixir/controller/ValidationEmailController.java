package com.elixir.controller;

import com.elixir.dao.UserDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class ValidationEmailController {

    @FXML
    private Label errorLabel;

    @FXML
    private TextField num1;

    @FXML
    private TextField num2;

    @FXML
    private TextField num3;

    @FXML
    private TextField num4;

    @FXML
    private TextField num5;

    @FXML
    private TextField num6;

    @FXML
    private Button verificationButton;

    @FXML
    private Button backButton;

    @FXML
    void verificationButtonAction(ActionEvent event) throws SQLException {
        ObjectSaveManager reader  = new ObjectSaveManager();
        User user = (User) reader.getObject("user");
        if (user == null){
            errorLabel.setText("Código inválido");
        }

        if (user.getCodeVerify().equals((num1.getText() + num2.getText() + num3.getText() + num4.getText() + num5.getText() + num6.getText()).toLowerCase())){
            user.setVerify(true);
            errorLabel.setText("Usuário confirmado com sucesso. Carregando...");

            var userDAO = new UserDAO();
            userDAO.update(user);

            PaneManager paneManager = new PaneManager();
            paneManager.openPane("login");
        } else {
            errorLabel.setText("Código inválido");
        }
    }

    @FXML
    public void backButtonAction(ActionEvent event){
        PaneManager manager = new PaneManager();
        manager.openPane("login");
    }

}
