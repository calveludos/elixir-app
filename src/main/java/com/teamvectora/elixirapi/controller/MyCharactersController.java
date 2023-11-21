package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.controller.objects.CharacterObject;
import com.teamvectora.elixirapi.controller.objects.FolderObject;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.*;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Folder;
import com.teamvectora.elixirapi.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.teamvectora.elixirapi.model.tables.TypeID.*;

public class MyCharactersController extends MenuController {

    @FXML
    private HBox hboxCharacters;
    @FXML
    private HBox hboxFolders;

    @FXML
    public void initialize(){
        super.initialize();

        ObjectSaveManager reader = new ObjectSaveManager();
        Map<Integer, CharacterMaster> characterMap = (Map<Integer, CharacterMaster>) reader.getObject("characters");
        Map<Integer, Folder> folderMap = (Map<Integer, Folder>) reader.getObject("folders");

        int defaultId = folderMap.values()
                .stream()
                .filter(f -> f.getName().equals("default"))
                .findFirst()
                .map(Folder::getId)
                .orElse(-1);
        folderMap.remove(defaultId);

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
            if (character.getFolderId() == defaultId)
                characterDefault.put(character.getId(), character);
        });

        if (!characterDefault.isEmpty()) {
            Integer[] indexs = characterDefault.keySet().toArray(new Integer[0]);
            for (int i = 0; i < characterDefault.size(); i = i + 2) {
                VBox vBox = new VBox();
                vBox.setPrefHeight(200.0);
                vBox.setPrefWidth(100.0);
                vBox.setSpacing(5);
                try {
                    CharacterMaster character1 = characterDefault.get(indexs[i]);
                    vBox.getChildren().add(new CharacterObject(character1));
                    CharacterMaster character2 = characterDefault.get(indexs[i+1]);
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
            case WARRIOR:
                return "Homem de Armas";
            case WIZARD:
                return "Mago";
            case THIEF:
                return "Ladrão";
            case CLERIC:
                return "Clérigo";
            default:
                return "";
        }
    }

    public static String getRaceId(int raceId) {
        switch (raceId){
            case HUMAN:
                return "Humano";
            case ELF:
                return "Elfo";
            case DWARF:
                return "Anão";
            case HALFLING:
                return "Halfing";
            default:
                return "";
        }
    }

}
