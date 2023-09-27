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
import java.util.Map;

public class myCharactersController {

    @FXML
    private Button createCharacterMenuButton;

    @FXML
    private Button myCharacterMenuButton;

    @FXML
    private HBox hboxCharacters;
    @FXML
    private HBox hboxFolders;

    private Map<Integer, Character> characterMap;
    private Map<Integer, Folder> folderMap;
    private Map<Integer, Attribute> attributeMap;

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
            attributeMap = attributeDAO.read();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
        
        try {
            FolderDAO folderDAO = new FolderDAO();
            folderMap = folderDAO.read();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }

        if(!folderMap.isEmpty()){
            for (Folder folder :
                    folderMap.values()) {
                VBox vBox = new VBox();
                vBox.setPrefHeight(200.0);
                vBox.setPrefWidth(100.0);
                vBox.getChildren().add(new FolderObject(folder.getName()));
                hboxFolders.getChildren().add(vBox);
            }
        }
        
        if (!characterMap.isEmpty()) {
            VBox vBox = new VBox();
            vBox.setPrefHeight(200.0);
            vBox.setPrefWidth(100.0);
            Integer[] indexs = characterMap.keySet().toArray(new Integer[0]);
            for (int i = 0; i < characterMap.size(); i = i + 2) {
                Character character1 = characterMap.get(indexs[i]);
                Character character2 = characterMap.get(indexs[i+1]);
                vBox.getChildren().add(new CharacterObject(character1.getName(), getRaceId(character1.getRaceId()), getClassId(character2.getClassId())));
                vBox.getChildren().add(new CharacterObject(character2.getName(), getRaceId(character2.getRaceId()), getClassId(character2.getClassId())));
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
    void characterPane(ActionEvent event){

    }

    public void createCharacterButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
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
