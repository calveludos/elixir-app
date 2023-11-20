package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.manager.JsonManger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    void initialize(){
        List<ItemComboBox> divineSpells = new ArrayList<>();
        List<ItemComboBox> arcaneSpells;

        try {
            JSONObject divineJson = ((JSONObject) JsonManger.get("divineSpells/Magias Divinas"));
            if (filterLevel1o.isSelected())
                divineSpells.add(((JSONArray) divineJson.get("1 Circulo"))
                        .stream()
                        .map(item -> new ItemComboBox(
                                ((JSONObject) item).get("name").toString(),
                                item.,
                                2
                        ));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void addButtonAction(ActionEvent event) {
    }
}
