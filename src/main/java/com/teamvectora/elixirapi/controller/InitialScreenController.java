package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InitialScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button logonButton;

    @FXML
    void initialize(){
        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.saveObject("offline", false);
    }

    @FXML
    void loginButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("login");
    }

    @FXML
    void logonButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("logon");
    }

}