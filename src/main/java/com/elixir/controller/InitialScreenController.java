package com.elixir.controller;

import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InitialScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button logonButton;

    @FXML
    void initialize(){
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
