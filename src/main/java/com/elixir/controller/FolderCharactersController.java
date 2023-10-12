package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import com.elixir.controller.objects.CharacterObject;
import com.elixir.controller.objects.FolderObject;
import com.elixir.dao.CharacterDAO;
import com.elixir.dao.FolderDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.model.Character;
import com.elixir.model.Folder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.Map;

import static com.elixir.controller.MyCharactersController.getClassId;
import static com.elixir.controller.MyCharactersController.getRaceId;

public class FolderCharactersController extends MenuController {

    @FXML
    private VBox charactersVBox;

    @FXML
    private void initialize() throws SQLException {
        ObjectSaveManager reader = new ObjectSaveManager();
        reader.printMap();
        Folder folder = (Folder) reader.getObject("folder");
        Map<Integer, Character> characterMap = (Map<Integer, Character>) reader.getObject("characters");
        characterMap.values().removeIf(c -> c.getFolderId() != folder.getId());

        Integer[] indexs = characterMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < characterMap.size(); i = i + 2) {
            HBox hBox = new HBox();
            hBox.setPrefHeight(200.0);
            hBox.setPrefWidth(100.0);
            hBox.setSpacing(10);
            try {
                Character character1 = characterMap.get(indexs[i]);
                hBox.getChildren().add(new CharacterObject(character1.getName(), getRaceId(character1.getRaceId()), getClassId(character1.getClassId())));
                Character character2 = characterMap.get(indexs[i+1]);
                hBox.getChildren().add(new CharacterObject(character2.getName(), getRaceId(character2.getRaceId()), getClassId(character2.getClassId())));
            } catch (IndexOutOfBoundsException ignored){}
            charactersVBox.getChildren().add(hBox);
        }
    }

    @FXML
    void newFolderButtonAction(ActionEvent event) {

    }

}