package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.manager.Tuple;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import com.elixir.model.Slots;
import com.elixir.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCharacterBackgroundController extends CreateCharacterSectionController {

    @FXML
    private TextArea backgroundField;

    @FXML
    private Button createCharacterButton;

    private Character character;
    private Attribute attribute;

    private Slots slots;
    private User user;

    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");
        attribute = (Attribute) reader.getObject("attribute");
        user = (User) reader.getObject("user");

        try{
            if(character.getBackground() != null){
                backgroundField.setText(character.getBackground());
            }
        } catch (NullPointerException e){
            character = new Character();
        }
    }
    @FXML
    void finishButtonAction(ActionEvent event) throws SQLException, IOException, ParseException {
        finishCharacter();
    }

    private void finishCharacter() throws SQLException, IOException, ParseException {
        AttributeDAO daoAttribute = new AttributeDAO();
        int attributeId = daoAttribute.create(attribute);


        List<Object> path = new ArrayList<>();
        path.add(getClass(character.getClassId()));
        path.add(new Tuple<>("level", character.level));
        path.add("XP");

        long classXP = (long) JsonManger.get("class", path);

        path.clear();
        path.add(new Tuple<>("race", character.getRaceId()));
        path.add("maxHeight");

        int maxHeight = (int) JsonManger.get("race", path);

        path.remove(path.size()-1);
        path.add("minHeight");

        int minHeight = (int) JsonManger.get("race", path);

        path.remove(path.size()-1);
        path.add("maxWeight");

        int maxWeight = (int) JsonManger.get("race", path);

        path.remove(path.size()-1);
        path.add("minWeight");

        int minWeight = (int) JsonManger.get("race", path);

        path.clear();
        path.add(new Tuple<>("alignments", character.getAlignmentId()));
        path.add("id");

        int aligmentId = (int) JsonManger.get("alignments", path);

        path.clear();
        path.add(new Tuple<>("class", character.getClassId()));
        path.add("dado de vida");

        int dicePV = (int) JsonManger.get("class", path);
        dicePV = Integer.parseInt(String.valueOf(dicePV).replace("d", ""));

        path.clear();

        character.setFolderId(1);
        character.setAttributeId(attributeId);
        character.setExperience((int) classXP);
        character.setHeight((maxHeight + minHeight) / 2);
        character.setWeight((maxWeight + minWeight) / 2);
        character.setMaxPv(attribute.getConstitution() + dicePV);
        character.setClassArmorBonus(attribute.getDexterity() + 10);

        CharacterDAO dao = new CharacterDAO();
        dao.create(character);

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("newCharacterPane");
    }


    @Override
    protected void saveCharacter(String fxml){
        if (character.getClassId() == 2) {
            slots.setILevel(1);
            Tuple<Character, Slots> characterWithSlots = new Tuple<>(character, slots);

            ObjectSaveManager saver = new ObjectSaveManager();
            saver.saveObject("character", characterWithSlots);

            PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
            paneManager.openPane(fxml);
        }

        else if (character.getClassId() == 4) {
            slots.setILevel(0);
            Tuple<Character, Slots> characterWithSlots = new Tuple<>(character, slots);

            ObjectSaveManager saver = new ObjectSaveManager();
            saver.saveObject("character", characterWithSlots);

            PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
            paneManager.openPane(fxml);
        }
        else {
            character.setBackground(backgroundField.getText());

            ObjectSaveManager saver = new ObjectSaveManager();
            saver.saveObject("character", character);

            PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
            paneManager.openPane(fxml);
        }

    }

    private String getClass(int classId){
        switch (classId){
            case 1:
                return "warrior";
            case 2:
                return "wizard";
            case 3:
                return "thief";
            case 4:
                return "cleric";
            default:
                return "";
        }
    }
}
