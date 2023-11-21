package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Inventory;
import com.teamvectora.elixirapi.model.Speech;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PopupSpeechController {

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<ItemComboBox> resultLanguagesComboBox;

    @FXML
    private TextField searchField;

    private static final class ItemComboBox{
        public String name;
        public int languageId;

        public ItemComboBox(String name, int languageId) {
            this.name = name;
            this.languageId = languageId;
        }

        public String getName() {
            return name;
        }

        public int getLanguageId(){
            return languageId;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @FXML
    void initialize(){

        List<ItemComboBox> allItems;

        try {
           allItems = new ArrayList<>(new ArrayList<ItemComboBox>(((JSONArray) JsonManger.get("languages/languages"))
                   .stream()
                   .map(item -> new ItemComboBox(
                           ((JSONObject) item).get("name").toString(),
                           Integer.parseInt(((JSONObject) item).get("id").toString())
                   ))
                   .toList()));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        
        resultLanguagesComboBox.getItems().addAll(allItems);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty() || newValue.isBlank()){
                resultLanguagesComboBox.getItems().setAll(allItems);
            } else {
                resultLanguagesComboBox.getItems().clear();
                for (ItemComboBox item : allItems) {
                    if (item.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        resultLanguagesComboBox.getItems().add(item);
                    }
                }

                if (resultLanguagesComboBox.getItems().size() > 0)
                    resultLanguagesComboBox.setPromptText(resultLanguagesComboBox.getItems().get(0).getName());
                else
                    resultLanguagesComboBox.setPromptText("Item não encontrado");

                resultLanguagesComboBox.show();
            }
        });

    }

    public void addButtonAction(ActionEvent event) {
        ObjectSaveManager saveManager = new ObjectSaveManager();
        CharacterMaster character = (CharacterMaster) saveManager.getObject("character");
        ItemComboBox itemComboBox = resultLanguagesComboBox.getValue();
        if (character.getSpeech() == null)
            character.setSpeech(new ArrayList<>());
        character.addSpeech(new Speech(character.getId(), itemComboBox.getLanguageId()));
        saveManager.saveObject("character", character);

        Stage stage = (Stage) resultLanguagesComboBox.getScene().getWindow();
        stage.close();
    }

}
