package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.controller.objects.CharacterObject;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Folder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class FolderCharactersController extends MenuController {

    @FXML
    private VBox charactersVBox;

    @FXML
    public void initialize() {
        super.initialize();

        ObjectSaveManager reader = new ObjectSaveManager();
        reader.printMap();
        Folder folder = (Folder) reader.getObject("folder");
        Map<Integer, CharacterMaster> characterMap = (Map<Integer, CharacterMaster>) reader.getObject("characters");
        characterMap.values().removeIf(c -> c.getFolderId() != folder.getId());

        Integer[] indexs = characterMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < characterMap.size(); i = i + 2) {
            HBox hBox = new HBox();
            hBox.setPrefHeight(200.0);
            hBox.setPrefWidth(100.0);
            hBox.setSpacing(10);
            try {
                CharacterMaster character1 = characterMap.get(indexs[i]);
                hBox.getChildren().add(new CharacterObject(character1));
                CharacterMaster character2 = characterMap.get(indexs[i+1]);
                hBox.getChildren().add(new CharacterObject(character2));
            } catch (IndexOutOfBoundsException ignored){}
            charactersVBox.getChildren().add(hBox);
        }
    }

    @FXML
    void addCharacterButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("newFolderCharacters");
    }

    @FXML
    void removeCharacterButtonAction(ActionEvent event){
        PaneManager manager = new PaneManager();
        manager.openPane("removeFolderCharacters");
    }

}