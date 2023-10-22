package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ViewCharacterPage2Controller extends MenuController {

    @FXML
    private Button addEquipamentButton;

    @FXML
    private Label addEquipamentLabel;

    @FXML
    private Button addLanguageButton;

    @FXML
    private Label addLanguageLabel;

    @FXML
    private TextArea appearanceField;

    @FXML
    private Button backButton;

    @FXML
    private TextField classAndRaceField;

    @FXML
    private TextField currentXPField;

    @FXML
    private ImageView emptyImage;

    @FXML
    private VBox inventoryVBox;

    @FXML
    private VBox languagesVBox;

    @FXML
    private Spinner<Integer> levelSpinner;

    @FXML
    private TextField nameCharacterField;

    @FXML
    private VBox newEquipamentsVBox;

    @FXML
    private VBox newLanguageVBox;

    @FXML
    private Button nextPageButton;

    @FXML
    private TextField nextXPField;

    @FXML
    private ImageView oldDragonLogo;

    @FXML
    private TextField piceOfCupperField;

    @FXML
    private TextField piceOfElectroField;

    @FXML
    private TextField piceOfGoldField;

    @FXML
    private TextField piceOfPlatineField;

    @FXML
    private TextField piceOfSilverField;

    @FXML
    private Button saveButton;
    private ObjectSaveManager reader;
    private Character character;
    private int nextLevelXP;

    @FXML
    public void initialize() {
        reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");

        setHeader();
        setEquipaments();
        setDeathLives();
        setThiefTalents();
        setLanguages();
        setCurrency();
        setXP();
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
                character.level = Integer.parseInt(String.valueOf((long) jsonObject.get("Nível"))) - 1;
                nextLevelXP = Integer.parseInt(String.valueOf((long) jsonObject.get("XP")));
                break;
            } else {
                maxXp = (long) jsonObject.get("XP");
            }
        }
        if (character.level == 0){
            character.level = 20;
        }

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelSpinner.setValueFactory(valueFactory);
        levelSpinner.getValueFactory().setValue(character.level);
        appearanceField.setText(character.getAppearance());

    }

    private void setEquipaments() {
    }

    private void setDeathLives() {

    }

    private void setThiefTalents() {

    }

    private void setLanguages() {

    }

    private void setCurrency() {
    }

    private void setXP() {
        currentXPField.setText(String.valueOf(character.getExperience()));
        nextXPField.setText( (nextLevelXP == 0)
                ? "MAX"
                : String.valueOf(nextLevelXP));
    }


    @FXML
    void addEquipamentButtonAction(ActionEvent event) {

    }

    @FXML
    void addLanguageButtonAction(ActionEvent event) {

    }

    @FXML
    void backButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage1");
    }

    @FXML
    void nextPageButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage3");
    }

    @FXML
    void saveButtonAction(ActionEvent event) {

    }

}
