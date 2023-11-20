package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Inventory;
import com.teamvectora.elixirapi.model.Spell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.teamvectora.elixirapi.model.tables.TypeID.ARCANE_SPELL;
import static com.teamvectora.elixirapi.model.tables.TypeID.DIVINE_SPELL;

public class PopupMagicController {
    public TextField searchField;
    public ComboBox<ItemComboBox> resultMagicComboBox;
    public Button addButton;
    public CheckBox filterDivineSpell;
    public CheckBox filterArcaneSpell;
    public CheckBox filterLevel1o;
    public CheckBox filterLevel2o;
    public CheckBox filterLevel3o;
    public CheckBox filterLevel4o;
    public CheckBox filterLevel5o;
    public CheckBox filterLevel6o;
    public CheckBox filterLevel7o;
    public CheckBox filterLevel8o;
    public CheckBox filterLevel9o;
    private List<ItemComboBox> allItems;

    private static final class ItemComboBox{
        public String name;
        public int idMagic;
        public int idTypeMagic;

        public ItemComboBox(String name, int idMagic, int idTypeMagic) {
            this.name = name;
            this.idMagic = idMagic;
            this.idTypeMagic = idTypeMagic;
        }

        public String getName() {
            return name;
        }

        public int getIdMagic() {
            return idMagic;
        }

        public int getIdTypeMagic() {
            return idTypeMagic;
        }

        @Override
        public String toString(){
            return name;
        }
    }

    @FXML
    private void filterAction(MouseEvent mouseEvent){
        searchRoutine();
    }

    private void searchRoutine() {
        List<ItemComboBox> divineSpells = new ArrayList<>();
        List<ItemComboBox> arcaneSpells = new ArrayList<>();

        try {
            if (filterDivineSpell.isSelected()) {
                JSONObject divineJson = ((JSONObject) JsonManger.get("divinesSpells/Magias Divinas"));
                if (filterLevel1o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("1 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
                if (filterLevel2o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("2 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
                if (filterLevel3o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("3 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
                if (filterLevel4o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("4 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
                if (filterLevel5o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("5 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
                if (filterLevel6o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("6 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
                if (filterLevel7o.isSelected())
                    divineSpells.addAll(((JSONArray) divineJson.get("7 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    DIVINE_SPELL
                            ))
                            .toList());
            }

            if (filterArcaneSpell.isSelected()) {
                JSONObject arcaneJson = ((JSONObject) JsonManger.get("arcaneSpells/Magias Arcanas"));
                if (filterLevel1o.isSelected()) {
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("1 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                }
                if (filterLevel2o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("2 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel3o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("3 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel4o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("4 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel5o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("5 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel6o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("6 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel7o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("7 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel8o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("8 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
                if (filterLevel9o.isSelected())
                    arcaneSpells.addAll(((JSONArray) arcaneJson.get("9 Circulo"))
                            .stream()
                            .map(item -> new ItemComboBox(
                                    ((JSONObject) item).get("name").toString(),
                                    Integer.parseInt(((JSONObject) item).get("id").toString()),
                                    ARCANE_SPELL
                            ))
                            .toList());
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        allItems = new ArrayList<>();
        allItems.addAll(divineSpells);
        allItems.addAll(arcaneSpells);

        resultMagicComboBox.getItems().clear();
        resultMagicComboBox.getItems().addAll(allItems);
    }

    @FXML
    void initialize(){

        filterLevel1o.setSelected(true);
        filterLevel2o.setSelected(true);
        filterLevel3o.setSelected(true);
        filterLevel4o.setSelected(true);
        filterLevel5o.setSelected(true);
        filterLevel6o.setSelected(true);
        filterLevel7o.setSelected(true);
        filterLevel8o.setSelected(true);
        filterLevel9o.setSelected(true);

        filterArcaneSpell.setSelected(true);
        filterDivineSpell.setSelected(true);

        searchRoutine();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty() || newValue.isBlank()){
                resultMagicComboBox.getItems().setAll(allItems);
            } else {
                resultMagicComboBox.getItems().clear();
                for (ItemComboBox item : allItems) {
                    if (item.getName().toLowerCase().contains(newValue.toLowerCase())) {
                        resultMagicComboBox.getItems().add(item);
                    }
                }
                if (resultMagicComboBox.getItems().size() > 0)
                    resultMagicComboBox.setPromptText(resultMagicComboBox.getItems().get(0).getName());
                else
                    resultMagicComboBox.setPromptText("Item não encontrado");

                resultMagicComboBox.show();
            }
        });
        
    }

    public void addButtonAction(ActionEvent event) {
        ObjectSaveManager saveManager = new ObjectSaveManager();
        CharacterMaster character = (CharacterMaster) saveManager.getObject("character");
        ItemComboBox itemComboBox = resultMagicComboBox.getValue();
        if (character.getSpells() == null)
            character.setSpells(new ArrayList<>());
        character.addSpell(new Spell(character.getId(), itemComboBox.getIdMagic(), itemComboBox.getIdTypeMagic()));
        saveManager.saveObject("character", character);

        Stage stage = (Stage) resultMagicComboBox.getScene().getWindow();
        stage.close();
    }
}
