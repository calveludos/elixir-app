package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import com.elixir.controller.objects.CharacterObject;
import com.elixir.controller.objects.FolderObject;
import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.dao.FolderDAO;
import com.elixir.factory.ConnectionFactory;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import com.elixir.model.Folder;
import com.elixir.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyCharactersController extends MenuController {

    @FXML
    private HBox hboxCharacters;
    @FXML
    private HBox hboxFolders;

    private Map<Integer, Character> characterMap;

    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager();
        int userId = ((User) reader.getObject("user")).getId();

        Map<Integer, Folder> folderMap;
        try {
            FolderDAO folderDAO = new FolderDAO();
            Folder filter = new Folder();
            filter.setUserId(userId);
            folderMap = folderDAO.read(filter);
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
        
        int defaultId = -1;
        for (Folder f :
                folderMap.values()) {
            if (f.getName().equals("default")){
                defaultId = f.getId();
            }
        }
        folderMap.remove(defaultId);

        ResultSet resultSet = null;

        String query = "SELECT c.* FROM `Character` c JOIN Folder f\n" +
                "ON c.id_folder = f.id \n" +
                "WHERE f.id_user = ?;";

        characterMap = new HashMap<>();

        try (
                Connection conn = ConnectionFactory.createConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, userId);

            resultSet = stmt.executeQuery();


            while (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getInt("id"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setFolderId(resultSet.getInt("id_folder"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name"));
                character.setExperience(resultSet.getInt("experience"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setAppearance(resultSet.getString("apperance"));
                character.setBackground(resultSet.getString("background"));
                character.setImagePath(resultSet.getString("image_path"));

                characterMap.put(character.getId(), character);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        var oldMap = characterMap;
        Map<Integer, Character> newMap = new HashMap<>();
        for (Character c : oldMap.values()) {
            if (c.getFolderId() == defaultId){
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
