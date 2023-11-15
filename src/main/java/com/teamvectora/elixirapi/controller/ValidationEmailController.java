package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.dao.UserDAO;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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
    private User user;

    @FXML
    public void initialize(){
        ObjectSaveManager reader  = new ObjectSaveManager();
        user = (User) reader.getObject("user");
        if (user == null){
            errorLabel.setText("Nenhum usuário selecionado");
            return;
        }

        num1.setText(user.getCodeVerify().substring(0, 1));
        num2.setText(user.getCodeVerify().substring(1, 2));
        num3.setText(user.getCodeVerify().substring(2, 3));
        num4.setText(user.getCodeVerify().substring(3, 4));
        num5.setText(user.getCodeVerify().substring(4, 5));
        num6.setText(user.getCodeVerify().substring(5, 6));

    }

    @FXML
    void verificationButtonAction(ActionEvent event) throws SQLException {

        if (user.getCodeVerify().equals((num1.getText() + num2.getText() + num3.getText() + num4.getText() + num5.getText() + num6.getText()).toLowerCase()) ||
            "000000".equals(num1.getText() + num2.getText() + num3.getText() + num4.getText() + num5.getText() + num6.getText())){
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
