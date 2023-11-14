package com.elixir.controller;

import com.elixir.manager.JsonManger;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.CharacterMaster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ViewCharacterPage3Controller {

    public TableView<SpellTable> spellsTableView;
    @FXML
    private Button addSpellButton;

    @FXML
    private TextArea appearanceField;

    @FXML
    private Button backButton;

    @FXML
    private TextField classAndRaceField;

    @FXML
    private TableColumn<SpellTable, String> descriptionSpeelColumn;

    @FXML
    private TableColumn<?, ?> durationSpeelColumn;

    @FXML
    private ImageView emptyImage;

    @FXML
    private TableColumn<SpellTable, String> levelSpeelColumn;

    @FXML
    private Spinner<Integer> levelSpinner;

    @FXML
    private TextField nameCharacterField;

    @FXML
    private TableColumn<SpellTable, String> nameSpeelColumn;

    @FXML
    private ImageView oldDragonLogo;

    @FXML
    private TableColumn<SpellTable, Double> rangeSpeelColumn;

    @FXML
    private Button saveButton;

    @FXML
    private TextField spell1;

    @FXML
    private TextField spell2;

    @FXML
    private TextField spell3;

    @FXML
    private TextField spell4;

    @FXML
    private TextField spell5;

    @FXML
    private TextField spell6;

    @FXML
    private TextField spell7;

    @FXML
    private TextField spell8;

    @FXML
    private TextField spell9;

    @FXML
    private TextField spellDay1;

    @FXML
    private TextField spellDay2;

    @FXML
    private TextField spellDay3;

    @FXML
    private TextField spellDay4;

    @FXML
    private TextField spellDay5;

    @FXML
    private TextField spellDay6;

    @FXML
    private TextField spellDay7;

    @FXML
    private TextField spellDay8;

    @FXML
    private TextField spellDay9;

    private CharacterMaster character;
    private int level;

    public static class SpellTable{
        public final String level;
        public final String name;
        public final String range;
        public final String duration;
        public final String description;

        public SpellTable(String level, String name, String range, String duration, String description) {
            this.level = level;
            this.name = name;
            this.range = range;
            this.duration = duration;
            this.description = description;
        }

        public String getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }

        public String getRange() {
            return range;
        }

        public String getDuration() {
            return duration;
        }

        public String getDescription() {
            return description;
        }
    }

    @FXML
    public void initialize(){
        ObjectSaveManager saveManager = new ObjectSaveManager();
        character = (CharacterMaster) saveManager.getObject("character");

        setHeader();
        setSpellsTable();
    }

    private void setSpellsTable() {
        levelSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        rangeSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("range"));
        nameSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        durationSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        ObservableList<SpellTable> observableList = FXCollections.observableList(new ArrayList<>());

        character.getSpells().forEach(spell -> {
            SpellTable spellTable = null;
            observableList.add(spellTable);
        });

        spellsTableView.setItems(observableList);

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
        appearanceField.setText(character.getAppearance());
    }

    @FXML
    void addSpellButtonAction(ActionEvent event) {

    }

    @FXML
    void backButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("ViewCharacterPage2");

    }

    @FXML
    void saveButtonAction(ActionEvent event) {

    }

}
