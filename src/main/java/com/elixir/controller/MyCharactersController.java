package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import com.elixir.controller.objects.CharacterObject;
import com.elixir.controller.objects.FolderObject;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.*;
import com.elixir.model.Character;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCharactersController extends MenuController {

    @FXML
    private HBox hboxCharacters;
    @FXML
    private HBox hboxFolders;

    @FXML
    public void initialize(){
        super.initialize();

        ObjectSaveManager reader = new ObjectSaveManager();
        int userId = ((User) reader.getObject("user")).getId();
        Map<Integer, CharacterMaster> characterMap = (Map<Integer, CharacterMaster>) reader.getObject("characters");
        Map<Integer, Folder> folderMap = (Map<Integer, Folder>) reader.getObject("folders");
        
        AtomicInteger defaultId = new AtomicInteger(-1);
        folderMap.values().forEach(folder -> {
            if (folder.getName().equals("default"))
                defaultId.set(folder.getId());
        });
        folderMap.remove(defaultId.get());

        if(!folderMap.isEmpty()){
            Integer[] indexs = folderMap.keySet().toArray(new Integer[0]);
            for (int i = 0; i < folderMap.size(); i = i + 3) {
                VBox vBox = new VBox();
                vBox.setPrefHeight(200.0);
                vBox.setPrefWidth(100.0);
                try {
                    Folder folder1 = folderMap.get(indexs[i]);
                    vBox.getChildren().add(new FolderObject(folder1));
                    Folder folder2 = folderMap.get(indexs[i + 1]);
                    vBox.getChildren().add(new FolderObject(folder2));
                    Folder folder3 = folderMap.get(indexs[i + 2]);
                    vBox.getChildren().add(new FolderObject(folder3));
                } catch (IndexOutOfBoundsException ignored){}

                hboxFolders.getChildren().add(vBox);
            }
        }

        Map<Integer, CharacterMaster> characterDefault = new HashMap<>();
        characterMap.values().forEach(character -> {
            if (character.getFolder().getId() == defaultId.get())
                characterDefault.put(character.getFolder().getId(), character);
        });

        if (!characterDefault.isEmpty()) {
            Integer[] indexs = characterMap.keySet().toArray(new Integer[0]);
            for (int i = 0; i < characterMap.size(); i = i + 2) {
                VBox vBox = new VBox();
                vBox.setPrefHeight(200.0);
                vBox.setPrefWidth(100.0);
                vBox.setSpacing(5);
                try {
                    CharacterMaster character1 = characterMap.get(indexs[i]);
                    vBox.getChildren().add(new CharacterObject(character1));
                    CharacterMaster character2 = characterMap.get(indexs[i+1]);
                    vBox.getChildren().add(new CharacterObject(character2));
                } catch (IndexOutOfBoundsException ignored){}
                hboxCharacters.getChildren().add(vBox);
            }
        } else {
            Label label = new Label("Nada aqui ainda");
            label.setPrefSize(595, 70);
            label.setTextFill(Color.valueOf("#110000"));
            label.setFont(new Font(18));
            label.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);

            hboxCharacters.getChildren().add(label);
        }

    }

    @FXML
    private void newFolderButtonAction(){
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("newFolderName");
    }

    public static String getClassId(int classId) {
        switch (classId){
            case 1:
                return "Homem de Armas";
            case 2:
                return "Mago";
            case 3:
                return "Ladrão";
            case 4:
                return "Clérigo";
            default:
                return "";
        }
    }

    public static String getRaceId(int raceId) {
        switch (raceId){
            case 1:
                return "Humano";
            case 2:
                return "Elfo";
            case 3:
                return "Anão";
            case 4:
                return "Halfing";
            default:
                return "";
        }
    }

}
