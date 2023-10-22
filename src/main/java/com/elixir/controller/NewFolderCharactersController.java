package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import com.elixir.dao.CharacterDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Character;
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
    private Map<Integer, Character> characterMap;
    private Folder folder;



    @FXML
    public void initialize() {
        super.initialize();

        saveManager = new ObjectSaveManager();
        characterMap = (Map<Integer, Character>) saveManager.getObject("characters");
        folder = (Folder) saveManager.getObject("folder");

        folderNameLabel.setText(folder.getName());

        for (Character character :
                characterMap.values()) {
            CheckMenuItem menuItem = new CheckMenuItem();
            menuItem.setText(character.getName());
            menuItem.setId(String.valueOf(character.getId()));
            menuItem.setOnAction(event -> {
                charactersMenuButton.setText(characterMap.get(Integer.parseInt(menuItem.getId())).getName());
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
                Character character = characterMap.get(Integer.parseInt(checkMenuItem.getId()));
                character.setFolderId(folder.getId());
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