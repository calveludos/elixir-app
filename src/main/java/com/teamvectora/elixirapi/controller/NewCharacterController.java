package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.teamvectora.elixirapi.manager.*;

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
