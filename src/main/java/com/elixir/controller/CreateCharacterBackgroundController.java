package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.dao.SlotsDAO;
import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.manager.Tuple;
import com.elixir.model.*;
import com.elixir.model.Character;
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
import java.util.*;

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

        long classXP = (long) JsonManger.get("class/" + getClass(character.getClassId()) + "/level:" + character.level + "/XP");
        double maxHeight = (double) JsonManger.get("race/race:" + character.getRaceId() + "/maxHeight");
        double minHeight = (double) JsonManger.get("race/race:" + character.getRaceId() + "/minHeight");
        long maxWeight = (long) JsonManger.get("race/race:" + character.getRaceId() + "/maxWeight");
        long minWeight = (long) JsonManger.get("race/race:" + character.getRaceId()  + "/minWeight");
        String jsonDicePv = (String) JsonManger.get("class/" + getClass(character.getClassId()) + "/Dado de Vida");
        int dicePV = Integer.parseInt(String.valueOf(jsonDicePv).replace("d", ""));
        var bonusPV = JsonManger.get("class/" + getClass(character.getClassId()) + "/level:" + character.level + "/Dado de Vida");
        int bonusDicePv = 0;

        System.out.println(bonusPV);
        if (bonusPV instanceof String){
            Object maxBonusPV = null;
            for (int i = 1; i <= character.level; i++) {
                maxBonusPV = JsonManger.get("class/" + getClass(character.getClassId()) + "/level:" + i + "/Dado de Vida");
                if (maxBonusPV instanceof String){
                    bonusDicePv = Integer.parseInt(((String) maxBonusPV).replace("PV", "").replace("+", "").trim());
                    maxBonusPV = i - 1;
                }
            }
            bonusPV = maxBonusPV;
        }

        ObjectSaveManager reader = new ObjectSaveManager();
        Map<Integer, Folder> folderMap = (Map<Integer, Folder>) reader.getObject("folders");

        int defaultId = folderMap.values()
                .stream()
                .filter(f -> f.getName().equals("default"))
                .findFirst()
                .map(Folder::getId)
                .orElse(-1);

        character.setFolderId(defaultId);
        character.setExperience((int) classXP);
        character.setHeight((int) ((maxHeight + minHeight) / 2));
        character.setWeight((int) ((maxWeight + minWeight) / 2));
        character.setMaxPv(((attribute.getConstitution() + dicePV) * character.level) + (int) bonusPV + bonusDicePv);
        character.setImagePath("default");
        character.setBackground(backgroundField.getText());


        AttributeDAO daoAttribute = new AttributeDAO();
        int attributeId = daoAttribute.create(attribute);
        character.setAttributeId(attributeId);

        CharacterDAO dao = new CharacterDAO();
        dao.create(character);

        PaneManager paneManager = new PaneManager();
        paneManager.openPane("newCharacterPane");


    }


    @Override
    protected void saveCharacter(String fxml){
        character.setBackground(backgroundField.getText());

        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);
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
