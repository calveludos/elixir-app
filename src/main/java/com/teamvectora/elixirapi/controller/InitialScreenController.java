package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.dao.UserDAO;
import com.teamvectora.elixirapi.factory.ConnectionFactory;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.manager.XMLManager;
import com.teamvectora.elixirapi.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class InitialScreenController {

    @FXML
    void loginButtonAction(ActionEvent event) {
        credentiaisRoutine();

        PaneManager manager = new PaneManager();
        manager.openPane("login");

    }

    @FXML
    void logonButtonAction(ActionEvent event){
        credentiaisRoutine();

        PaneManager manager = new PaneManager();
        manager.openPane("logon");
    }

    private void credentiaisRoutine(){
        UserDAO userDAO = new UserDAO();
        Map<Integer, User> userMap = null;
        try {
            userMap = userDAO.read();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Credenciais para o MySQL");
            alert.setHeaderText("Credenciais Inválidas");
            alert.setContentText("Parece que as credenciais para a conexeção foram recusadas, deseja editá-las?");

            alert.showAndWait().ifPresent(response -> {
                if (response.getText().equals("OK")) {
                    PaneManager manager = new PaneManager();
                    manager.openPane("credentiaisPopup");
                }
            });
            throw new RuntimeException(e);
        }
    }

    public void changeCredentialsAction(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Credenciais para o MySQL");
        alert.setHeaderText("Mudança de dredenciais");
        alert.setContentText("Deseja altera suas credenciais de conexeção com o MySQL?");

        alert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                PaneManager manager = new PaneManager();
                manager.openPane("credentiaisPopup");
            }
        });
    }
}