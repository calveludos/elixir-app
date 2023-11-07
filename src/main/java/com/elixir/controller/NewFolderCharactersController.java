package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import com.elixir.dao.CharacterDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Character;
import com.elixir.model.CharacterMaster;
import com.elixir.model.Folder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.Map;

public class NewFolderCharactersController extends MenuController {

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private MenuButton charactersMenuButton;

    @FXML
    private Button createButton;

    @FXML
    private Button jumpButton;

    @FXML
    private Label folderNameLabel;

    private ObjectSaveManager saveManager;
    private Map<Integer, CharacterMaster> characterMap;
    private Folder folder;

    @FXML
    public void initialize() {
        super.initialize();

        saveManager = new ObjectSaveManager();
        characterMap = (Map<Integer, CharacterMaster>) saveManager.getObject("characters");
        folder = (Folder) saveManager.getObject("folder");

        folderNameLabel.setText(folder.getName());

        for (CharacterMaster character :
                characterMap.values()) {
            CheckMenuItem menuItem = new CheckMenuItem();
            menuItem.setText(character.getCharacter().getName());
            menuItem.setId(String.valueOf(character.getCharacter().getId()));
            menuItem.setOnAction(event -> {
                charactersMenuButton.setText(character.getCharacter().getName());
            });
            charactersMenuButton.getItems().add(menuItem);
        }

    }

    @FXML
    void createButtonAction(ActionEvent event) {
        CharacterDAO dao = new CharacterDAO();
        for (MenuItem menuItem :
                charactersMenuButton.getItems()) {
            CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
            if (checkMenuItem.isSelected()){
                System.out.println("entrou");
                CharacterMaster character = characterMap.get(Integer.parseInt(checkMenuItem.getId()));
                character.getCharacter().setFolderId(folder.getId());
                character.setFolder(folder);
                characterMap.put(character.getId(), character);
                try {
                    dao.update(character);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        saveManager.saveObject("characters", characterMap);
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("myCharactersPane");

    }

    @FXML
    void jumpButtonAction(ActionEvent event) {
        saveManager.removeObject("character");
        saveManager.removeObject("folder");
        saveManager.saveObject("characters", characterMap);
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("myCharactersPane");
    }

}