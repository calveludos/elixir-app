package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PopupEquipamentController {
    public ComboBox<ItemComboBox> resultNameComboBox;
    public Button addButton;
    public TextField searchField;

    private static final class ItemComboBox{
        public String name;
        public int typeId;
        public int itemId;

        public ItemComboBox(String name, int typeId, int itemId) {
            this.name = name;
            this.typeId = typeId;
            this.itemId = itemId;
        }

        public String getName() {
            return name;
        }

        public int getTypeId() {
            return typeId;
        }

        public int getItemId() {
            return itemId;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @FXML
    void initialize(){
        List<ItemComboBox> shields;
        List<ItemComboBox> armors;
        List<ItemComboBox> weapons;
        try {
            shields = new ArrayList<>(((JSONArray) JsonManger.get("shields/shields"))
                    .stream()
                    .filter(item -> ((JSONObject) item).size() > 1)
                    .map(item -> new ItemComboBox(
                            ((JSONObject) item).get("name").toString(),
                            3,
                            Integer.parseInt(((JSONObject) item).get("id").toString())
                    ))
                    .toList());
            armors = new ArrayList<>(((JSONArray) JsonManger.get("armor/armors"))
                    .stream()
                    .filter(item -> ((JSONObject) item).size() > 1)
                    .map(item -> new ItemComboBox(
                            ((JSONObject) item).get("name").toString(),
                            2,
                            Integer.parseInt(((JSONObject) item).get("id").toString())
                    ))
                    .toList());
            weapons = new ArrayList<>(((JSONArray) JsonManger.get("weapons/weapons"))
                    .stream()
                    .filter(item -> ((JSONObject) item).size() > 1)
                    .map(item -> new ItemComboBox(
                            ((JSONObject) item).get("name").toString(),
                            1,
                            Integer.parseInt(((JSONObject) item).get("id").toString())
                    ))
                    .toList());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        List<ItemComboBox> allItems = new ArrayList<>();
        allItems.addAll(armors);
        allItems.addAll(shields);
        allItems.addAll(weapons);

        System.out.println(allItems);

        resultNameComboBox.getItems().addAll(allItems);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty() || newValue.isBlank()){
                resultNameComboBox.getItems().setAll(allItems);
            } else {
                resultNameComboBox.getItems().clear();
                for (ItemComboBox item : allItems) {
                    if (item.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        resultNameComboBox.getItems().add(item);
                    }
                }

                if (resultNameComboBox.getItems().size() > 0)
                    resultNameComboBox.setPromptText(resultNameComboBox.getItems().get(0).getName());
                else
                    resultNameComboBox.setPromptText("Item não encontrado");

                resultNameComboBox.show();
            }
        });
        
    }

    public void addButtonAction(ActionEvent event) {
        ObjectSaveManager saveManager = new ObjectSaveManager();
        CharacterMaster character = (CharacterMaster) saveManager.getObject("character");
        ItemComboBox itemComboBox = resultNameComboBox.getValue();
        if (character.getInventory() == null)
            character.setInventory(new ArrayList<>());
        character.addInventory(new Inventory(character.getId(), itemComboBox.getItemId(), itemComboBox.getTypeId()));
        saveManager.saveObject("character", character);

        Stage stage = (Stage) resultNameComboBox.getScene().getWindow();
        stage.close();
    }
}
