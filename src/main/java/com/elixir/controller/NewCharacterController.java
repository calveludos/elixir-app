package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.elixir.manager.*;

public class NewCharacterController extends MenuController {

    @FXML
    private Button createNewCharacterButton;

    @FXML
    public void initialize(){
        super.initialize();

        ObjectSaveManager objectSaveManager = new ObjectSaveManager();
        if (objectSaveManager.getObject("character") != null){
            objectSaveManager.removeObject("character");
        }
    }

    @FXML
    void createNewCharacterButtonAction(ActionEvent event) {

        PaneManager paneManager = new PaneManager();
        paneManager.openPane("createCharacterDatePane");
    }

}
