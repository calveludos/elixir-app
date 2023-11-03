package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.*;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class CreateCharacterBackgroundController extends CreateCharacterSectionController {

    @FXML
    private TextArea backgroundField;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Label errorLabel;

    private Character character;
    private Attribute attribute;

    private Slots slots;
    private User user;

    @FXML
    public void initialize(){
        super.initialize();

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
        System.out.println(jsonDicePv);
        int dicePV = Integer.parseInt(String.valueOf(jsonDicePv).replace("d", ""));

        int totalBonusPV = 0;
        for (int i = 1; i <= character.level; i++) {
            String levelBonusPV = String.valueOf(JsonManger.get("class/" + getClass(character.getClassId()) + "/level:" + i + "/Dado de Vida"));
            if (levelBonusPV.contains("PV")){
                totalBonusPV += Integer.parseInt(levelBonusPV.replace("PV", ""));
            } else {
                totalBonusPV += dicePV;
            }
        }
        System.out.println(totalBonusPV);

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
        character.setMaxPv((int) (((Math.round((float) ((attribute.getConstitution() - 10) / 2) + 0.5) + dicePV) * character.level) + totalBonusPV));
        character.setImagePath("default");
        character.setBackground(backgroundField.getText());


        System.out.println(character);

        try {
            AttributeDAO attributeDAO = new AttributeDAO();
            attribute.setId(attributeDAO.create(attribute));

            character.setAttributeId(attribute.getId());

            CharacterDAO dao = new CharacterDAO();
            character.setId(dao.create(character));
        } catch (SQLException e){
            errorLabel.setText("Preencha todos os campos corretamente para criar o seu personagem");
            throw e;
        }

        Map<Integer, Character> characters = (Map<Integer, Character>) reader.getObject("characters");
        Map<Integer, Attribute> attributeMap = (Map<Integer, Attribute>) reader.getObject("attributes");
        characters.put(character.getId(), character);
        attributeMap.put(attribute.getId(), attribute);

        reader.saveObject("characters", characters);
        reader.saveObject("attributes", attributeMap);

        PaneManager paneManager = new PaneManager();
        paneManager.openPane("myCharactersPane");


    }


    @Override
    protected void saveCharacter(String fxml){
        character.setBackground(backgroundField.getText());

        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);

    }

    public static String getClass(int classId){
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
