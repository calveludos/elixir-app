package com.elixir.controller;

import com.elixir.controller.objects.CharacterObject;
import com.elixir.controller.objects.FolderObject;
import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.dao.FolderDAO;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import com.elixir.model.Folder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import com.elixir.manager.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class myCharactersController extends MenuController {

    @FXML
    private HBox hboxCharacters;
    @FXML
    private HBox hboxFolders;

    private Map<Integer, Character> characterMap;

    @FXML
    private void initialize(){

        try {
            CharacterDAO characterDAO = new CharacterDAO();
            characterMap = characterDAO.read();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            AttributeDAO attributeDAO = new AttributeDAO();
            Map<Integer, Attribute> attributeMap = attributeDAO.read();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }

        Map<Integer, Folder> folderMap;
        try {
            FolderDAO folderDAO = new FolderDAO();
            folderMap = folderDAO.read();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }

        if(!folderMap.isEmpty()){
            Integer[] indexs = folderMap.keySet().toArray(new Integer[0]);
            for (int i = 0; i < folderMap.size(); i = i + 3) {
                VBox vBox = new VBox();
                vBox.setPrefHeight(200.0);
                vBox.setPrefWidth(100.0);
                try {
                    Folder folder1 = folderMap.get(indexs[i]);
                    vBox.getChildren().add(new FolderObject(folder1.getName()));
                    Folder folder2 = folderMap.get(indexs[i + 1]);
                    vBox.getChildren().add(new FolderObject(folder2.getName()));
                    Folder folder3 = folderMap.get(indexs[i + 2]);
                    vBox.getChildren().add(new FolderObject(folder3.getName()));
                } catch (IndexOutOfBoundsException ignored){}

                hboxFolders.getChildren().add(vBox);
            }
        }

        var oldMap = characterMap;
        Map<Integer, Character> newMap = new HashMap<>();
        for (Character c : oldMap.values()) {
            if (c.getIdFolder() == 0){
                newMap.put(c.getId(), c);
            }
        }
        for (Character c :
                newMap.values()) {
            System.out.println(c);
        }

        characterMap = newMap;

        if (!characterMap.isEmpty()) {
            Integer[] indexs = characterMap.keySet().toArray(new Integer[0]);
            for (int i = 0; i < characterMap.size(); i = i + 2) {
                VBox vBox = new VBox();
                vBox.setPrefHeight(200.0);
                vBox.setPrefWidth(100.0);
                vBox.setSpacing(5);
                try {
                    Character character1 = characterMap.get(indexs[i]);
                    vBox.getChildren().add(new CharacterObject(character1.getName(), getRaceId(character1.getRaceId()), getClassId(character1.getClassId())));
                    Character character2 = characterMap.get(indexs[i+1]);
                    vBox.getChildren().add(new CharacterObject(character2.getName(), getRaceId(character2.getRaceId()), getClassId(character2.getClassId())));
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

    }

    private String getClassId(int classId) {
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

    private String getRaceId(int raceId) {
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
