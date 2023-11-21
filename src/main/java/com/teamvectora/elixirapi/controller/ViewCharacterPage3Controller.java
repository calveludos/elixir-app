package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Slots;
import com.teamvectora.elixirapi.model.Spell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.teamvectora.elixirapi.model.tables.TypeID.*;

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
        public final int spellId;
        public final int spellTypeId;

        public SpellTable(String level, String name, String range, String duration, String description, int spellId, int spellTypeId) {
            this.level = level;
            this.name = name;
            this.range = range;
            this.duration = duration;
            this.description = description;
            this.spellId = spellId;
            this.spellTypeId = spellTypeId;
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


        public int getSpellId() {
            return spellId;
        }

        public int getTypeSpellId() {
            return spellTypeId;
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
        if (character.getSlots() != null && (character.getClassId() == CLERIC || character.getClassId() == WIZARD)) {
            try {
                setSlots();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        else
            setNotSlots();
    }

    private void setSlots() throws IOException, ParseException {

        if (character.getClassId() == CLERIC){
            spell1.setText(JsonManger.get("class/cleric/level:" + character.level + "/1o").toString());
            spell2.setText(JsonManger.get("class/cleric/level:" + character.level + "/2o").toString());
            spell3.setText(JsonManger.get("class/cleric/level:" + character.level + "/3o").toString());
            spell4.setText(JsonManger.get("class/cleric/level:" + character.level + "/4o").toString());
            spell5.setText(JsonManger.get("class/cleric/level:" + character.level + "/5o").toString());
            spell6.setText(JsonManger.get("class/cleric/level:" + character.level + "/6o").toString());
            spell7.setText(JsonManger.get("class/cleric/level:" + character.level + "/7o").toString());
            spell8.setText("-");
            spell9.setText("-");

        } else if (character.getClassId() == WIZARD){
            spell1.setText(JsonManger.get("class/wizard/level:" + character.level + "/1o").toString());
            spell2.setText(JsonManger.get("class/wizard/level:" + character.level + "/2o").toString());
            spell3.setText(JsonManger.get("class/wizard/level:" + character.level + "/3o").toString());
            spell4.setText(JsonManger.get("class/wizard/level:" + character.level + "/4o").toString());
            spell5.setText(JsonManger.get("class/wizard/level:" + character.level + "/5o").toString());
            spell6.setText(JsonManger.get("class/wizard/level:" + character.level + "/6o").toString());
            spell7.setText(JsonManger.get("class/wizard/level:" + character.level + "/7o").toString());
            spell8.setText(JsonManger.get("class/wizard/level:" + character.level + "/8o").toString());
            spell9.setText(JsonManger.get("class/wizard/level:" + character.level + "/9o").toString());
        }

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

    private void setNotSlots() {
        spell1.setText("-");
        spell2.setText("-");
        spell3.setText("-");
        spell4.setText("-");
        spell5.setText("-");
        spell6.setText("-");
        spell7.setText("-");
        spell8.setText("-");
        spell9.setText("-");

        spellDay1.setText("-");
        spellDay2.setText("-");
        spellDay3.setText("-");
        spellDay4.setText("-");
        spellDay5.setText("-");
        spellDay6.setText("-");
        spellDay7.setText("-");
        spellDay8.setText("-");
        spellDay9.setText("-");
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
                                spellObject.get("description").toString(),
                                spell.getSpellId(),
                                spell.getTypeSpellId()
                        );
                    }
                }
            }

            observableList.add(spellTable);


        });


        spellsTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                int selectedIndex = spellsTableView.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("REMOVER");
                    alert.setHeaderText("Remover Magia");
                    alert.setContentText("Você tem certeza que deseja remover essa magia?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response.getText().equals("OK")){
                            character.setSpells(new ArrayList<>(character.getSpells()
                                    .stream()
                                    .filter(spell -> spell.getTypeSpellId() != spellsTableView.getItems().get(selectedIndex).getTypeSpellId() ||
                                                spell.getSpellId() != spellsTableView.getItems().get(selectedIndex).getSpellId())
                                    .toList()));

                            spellsTableView.getItems().remove(selectedIndex);
                        }
                    });
                }
            }
        });

        spellsTableView.setItems(observableList);

    }

    private void setHeader() {
        nameCharacterField.setText(character.getName());
        String clas = MyCharactersController.getClassId(character.getClassId());
        String race = MyCharactersController.getRaceId(character.getRaceId());
        classAndRaceField.setText(clas.toUpperCase() + " / " + race.toUpperCase());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelSpinner.setValueFactory(valueFactory);
        levelSpinner.getValueFactory().setValue(character.level);
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
            popupStage.setTitle("MAGIAS");
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
        saveCharacter();

        PaneManager paneManager = new PaneManager();
        paneManager.openPane("ViewCharacterPage2");

    }

    @FXML
    void saveButtonAction(ActionEvent event) {
        saveCharacter();
    }

    public void saveCharacter(){
        int spell1Number = spell1.getText().equals("-") ? 0 : Integer.parseInt(spell1.getText());
        int spell2Number = spell2.getText().equals("-") ? 0 : Integer.parseInt(spell2.getText());
        int spell3Number = spell3.getText().equals("-") ? 0 : Integer.parseInt(spell3.getText());
        int spell4Number = spell4.getText().equals("-") ? 0 : Integer.parseInt(spell4.getText());
        int spell5Number = spell5.getText().equals("-") ? 0 : Integer.parseInt(spell5.getText());
        int spell6Number = spell6.getText().equals("-") ? 0 : Integer.parseInt(spell6.getText());
        int spell7Number = spell7.getText().equals("-") ? 0 : Integer.parseInt(spell7.getText());
        int spell8Number = spell8.getText().equals("-") ? 0 : Integer.parseInt(spell8.getText());
        int spell9Number = spell9.getText().equals("-") ? 0 : Integer.parseInt(spell9.getText());

        int slotsId;
        if (character.getSlots() != null)
            slotsId = character.getSlots().getId();
        else
            slotsId = 0;
        character.setSlots(new Slots(character.getId(), spell1Number, spell2Number, spell3Number, spell4Number, spell5Number, spell6Number, spell7Number, spell8Number, spell9Number));
        character.getSlots().setId(slotsId);

        character.setName(nameCharacterField.getText());
        character.setAppearance(appearanceField.getText());
        character.level = levelSpinner.getValue();

        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.saveObject("character", character);
    }

}
