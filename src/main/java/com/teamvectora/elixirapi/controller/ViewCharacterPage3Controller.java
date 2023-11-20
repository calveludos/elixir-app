package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Spell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.teamvectora.elixirapi.model.tables.TypeID.ARCANE_SPELL;
import static com.teamvectora.elixirapi.model.tables.TypeID.CLERIC;

public class ViewCharacterPage3Controller extends MenuController {

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
        super.addHeader();

        ObjectSaveManager saveManager = new ObjectSaveManager();
        character = (CharacterMaster) saveManager.getObject("character");

        setHeader();
        if (character.getSpells() != null)
            setSpellsTable();
        if (character.getSlots() != null)
            setSlots();
    }

    private void setSlots() {
        spell1.setText(String.valueOf(character.getSlots().getILevel()));
        spell2.setText(String.valueOf(character.getSlots().getIiLevel()));
        spell3.setText(String.valueOf(character.getSlots().getIiiLevel()));
        spell4.setText(String.valueOf(character.getSlots().getIvLevel()));
        spell5.setText(String.valueOf(character.getSlots().getVLevel()));
        spell6.setText(String.valueOf(character.getSlots().getViLevel()));
        spell7.setText(String.valueOf(character.getSlots().getViiLevel()));
        spell8.setText(String.valueOf(character.getSlots().getViiiLevel()));
        spell9.setText(String.valueOf(character.getSlots().getIxLevel()));
        
        spellDay1.setText(String.valueOf(character.getSlots().getILevel()));
        spellDay2.setText(String.valueOf(character.getSlots().getIiLevel()));
        spellDay3.setText(String.valueOf(character.getSlots().getIiiLevel()));
        spellDay4.setText(String.valueOf(character.getSlots().getIvLevel()));
        spellDay5.setText(String.valueOf(character.getSlots().getVLevel()));
        spellDay6.setText(String.valueOf(character.getSlots().getViLevel()));
        spellDay7.setText(String.valueOf(character.getSlots().getViiLevel()));
        spellDay8.setText(String.valueOf(character.getSlots().getViiiLevel()));
        spellDay9.setText(String.valueOf(character.getSlots().getIxLevel()));
    }

    private void setSpellsTable() {
        levelSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        rangeSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("range"));
        nameSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        durationSpeelColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        ObservableList<SpellTable> observableList = FXCollections.observableList(new ArrayList<>());

        character.getSpells().forEach(spell -> {
            JSONObject spellJson;
            try {
                spellJson = ((JSONObject) JsonManger.get(spell.getTypeSpellId() == ARCANE_SPELL
                        ? "arcaneSpells/Magias Arcanas"
                        : "divinesSpells/Magias Divinas"));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

            String[] levelsSpellsString = {
                    "1 Circulo", "2 Circulo", "3 Circulo",
                    "4 Circulo", "5 Circulo", "6 Circulo",
                    "7 Circulo", "8 Circulo", "9 Circulo",
            };

            SpellTable spellTable = null;
            for (int i = 0; i < spellJson.size(); i++) {
                JSONArray spellsArray = (JSONArray) spellJson.get(levelsSpellsString[i]);
                for (Object o :
                        spellsArray) {
                    JSONObject spellObject = (JSONObject) o;
                    if (Integer.parseInt(spellObject.get("id").toString()) == spell.getSpellId()){
                        spellTable = new SpellTable(
                                levelsSpellsString[i].replace(" ", "° "),
                                spellObject.get("name").toString(),
                                spellObject.get("range").toString(),
                                spellObject.get("duration").toString(),
                                spellObject.get("description").toString()
                        );
                    }
                }
            }

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
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        try {
            popupStage.setScene(new Scene(PaneManager.loadFXML("popupMagic")));
            popupStage.setResizable(false);
            popupStage.show();
            popupStage.setOnHidden(windowEvent -> {
                ObjectSaveManager saveManager = new ObjectSaveManager();
                character = (CharacterMaster) saveManager.getObject("character");
                if (character.getSpells() != null)
                    setSpellsTable();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
