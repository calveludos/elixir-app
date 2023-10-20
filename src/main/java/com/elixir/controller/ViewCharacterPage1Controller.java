package com.elixir.controller;

import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ViewCharacterPage1Controller {

    @FXML
    private TextField addsMagicField;

    @FXML
    private TextArea appereanceField;

    @FXML
    private TextField baField;

    @FXML
    private Button backButton;

    @FXML
    private TextField caField;

    @FXML
    private TextField chaField;

    @FXML
    private TextField chargeAndMaxField;

    @FXML
    private TextField classAndRaceField;

    @FXML
    private TextField conField;

    @FXML
    private TextField currentPvField;

    @FXML
    private TextField damageAjustField;

    @FXML
    private TextField deathLiveField;

    @FXML
    private TextField dexAjustsField;

    @FXML
    private TextField dexField;

    @FXML
    private ImageView emptyImage;

    @FXML
    private TextField followersField;

    @FXML
    private TextField intField;

    @FXML
    private TextField jpField;

    @FXML
    private TextField languagesField;

    @FXML
    private TextField learnMagicField;

    @FXML
    private Spinner<Integer> levelSpinner;

    @FXML
    private TextField maxPvField;

    @FXML
    private TextField mvField;

    @FXML
    private TextField nameCharacterField;

    @FXML
    private Button nextPageButton;

    @FXML
    private ImageView oldDragonLogo;

    @FXML
    private TextField protectionAjustField;

    @FXML
    private TextField pvAndProtectionAjust;

    @FXML
    private TextField reactionField;

    @FXML
    private TextField resurrectionField;

    @FXML
    private ImageView saveEditButton;

    @FXML
    private TextField strField;

    @FXML
    private TextField thiefTalentsField;

    @FXML
    private TextField wisField;

    private Character character;
    private Attribute attribute;
    private int level = 0;

    @FXML
    public void initialize() throws IOException, ParseException {
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");
        attribute = (Attribute) reader.getObject("attribute");

        setHeader();
        setAttributes();
        setSubAttributes();
        setAjusts();

    }

    private void setHeader() {
        nameCharacterField.setText(character.getName());
        String clas = MyCharactersController.getClassId(character.getClassId());
        String race = MyCharactersController.getRaceId(character.getRaceId());
        classAndRaceField.setText(clas.toUpperCase() + " / " + race.toUpperCase());

        JSONArray levelsArray;

        try {
            levelsArray = (JSONArray) JsonManger.get("class/" + CreateCharacterBackgroundController.getClass(character.getClassId()) + "/level");
        } catch (IOException e){
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long maxXp = 0;
        for (Object json :
                levelsArray) {
            JSONObject jsonObject = (JSONObject) json;
            System.out.println("max xp " + maxXp);
            System.out.println("char xp " + character.getExperience());
            if (character.getExperience() <= maxXp){
                level = Integer.parseInt(String.valueOf((long) jsonObject.get("Nível"))) - 1;
                break;
            } else {
                maxXp = (long) jsonObject.get("XP");
            }
        }
        if (level == 0){
            level = 20;
        }

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelSpinner.setValueFactory(valueFactory);
        levelSpinner.getValueFactory().setValue(level);
        appereanceField.setText(character.getAppearance());
    }

    private void setAttributes() {
        strField.setText(String.valueOf(attribute.getStrength()));
        chaField.setText(String.valueOf(attribute.getCharisma()));
        dexField.setText(String.valueOf(attribute.getDexterity()));
        conField.setText(String.valueOf(attribute.getConstitution()));
        intField.setText(String.valueOf(attribute.getIntelligence()));
        wisField.setText(String.valueOf(attribute.getWisdom()));
    }

    private void setSubAttributes() throws IOException, ParseException {
        maxPvField.setText(String.valueOf(character.getMaxPv()));
        currentPvField.setText(String.valueOf(character.getCurrentPv()));
        System.out.println("Level" + level);
        String mv = String.valueOf((long) JsonManger.get("race/race:" + character.getRaceId() + "/moveBase" ));
        String jp = String.valueOf((long) JsonManger.get("class/" + CreateCharacterBackgroundController.getClass(character.getClassId()) + "/level:" + level + "/Jogada de Proteção"));

        mvField.setText(mv);
        jpField.setText(jp);

    }

    private void setAjusts() {
    }

    @FXML
    void backButtonAction(ActionEvent event) {
        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.removeObject("character");

        PaneManager manager = new PaneManager();
        manager.openPane("myCharactersPane");
    }

    @FXML
    void nextPageButtonAction(ActionEvent event) {

    }

}
