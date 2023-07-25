package com.elixir;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.elixir.model.Character;
import com.elixir.model.Attribute;
import javafx.stage.Stage;

public class CharacterViewController {

    @FXML
    private Label aligmentLabel;

    @FXML
    private Label arcaneMagicLabel;

    @FXML
    private Label armorCALabel;

    @FXML
    private Label armorLabel;

    @FXML
    private Label attackAjustLabel;

    @FXML
    private Label attackDistanceAjustLabel;

    @FXML
    private Label baseJPLabel;

    @FXML
    private HBox bodyPane;

    @FXML
    private VBox bodySheet;

    @FXML
    private Label bonusCALabel;

    @FXML
    private Label caLabel;

    @FXML
    private Label chaLabel;

    @FXML
    private Label chargeLabel;

    @FXML
    private Label chargeMovimentLabel;

    @FXML
    private Label classEspecLabel;

    @FXML
    private Label conJPLabel;

    @FXML
    private Label conLabel;

    @FXML
    private Button createCharacterMenuButton;

    @FXML
    private Label cupperLabel;

    @FXML
    private Label currentPVLabel;

    @FXML
    private Label damageAjustLabel;

    @FXML
    private Label defenseAjustLabel;

    @FXML
    private Label dexCALabel;

    @FXML
    private Label dexJPLabel;

    @FXML
    private Label dexLabel;

    @FXML
    private Label divineMagicLabel;

    @FXML
    private Label electrumLabel;

    @FXML
    private Label followLabel;

    @FXML
    private Label goldLabel;

    @FXML
    private Label intLabel;

    @FXML
    private Label jpLabel;

    @FXML
    private Label languagesLabel;

    @FXML
    private Label learnMagicLabel;

    @FXML
    private VBox leftMenu;

    @FXML
    private Label levelLabel;

    @FXML
    private Label maxPVLabel;

    @FXML
    private Label monsterLabel;

    @FXML
    private Label movimentLabel;

    @FXML
    private Button myCharacterMenuButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label platinaLabel;

    @FXML
    private Label playerNameLabel;

    @FXML
    private Label protectionConAjustLabel;

    @FXML
    private Label protectionWisAjustLabel;

    @FXML
    private Label pvAjustLabel;

    @FXML
    private Label raceCALabel;

    @FXML
    private Label raceLabel;

    @FXML
    private Label raceMovimentLabel;

    @FXML
    private Label reactionAjustLabel;

    @FXML
    private Label resurrectionLabel;

    @FXML
    private VBox root;

    @FXML
    private Label silverLabel;

    @FXML
    private Label strLabel;

    @FXML
    private Label surpriseAjustLabel;

    @FXML
    private HBox topMenu;

    @FXML
    private Label wisJPLabel;

    @FXML
    private Label wisLabel;


    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager<>();
        Character character = (Character) reader.getObject("character");
        Attribute attribute = (Attribute) reader.getObject("attribute");

        reader.printMap();

        strLabel.setText(string(attribute.getStrength()));
        dexLabel.setText(string(attribute.getDexterity()));
        conLabel.setText(string(attribute.getConstitution()));
        wisLabel.setText(string(attribute.getWisdom()));
        chaLabel.setText(string(attribute.getCharisma()));
        intLabel.setText(string(attribute.getIntelligence()));

        armorCALabel.setText(string(character.getClassArmorBonus()));
        maxPVLabel.setText(string(character.getMaxPv()));
        currentPVLabel.setText(string(character.getCurrentPv()));
        nameLabel.setText(character.getName());
        playerNameLabel.setText(character.getPlayerName());
        levelLabel.setText(string(character.getExperience()));

        CharacterClass characterClass = CharacterClass.setCharacterClass(character.getClassId());
        Race race = Race.setRace(character.getRaceId());
        Alignment alignment = Alignment.setAlignment(character.getAlignmentId());

        aligmentLabel.setText(alignment.text);
        classEspecLabel.setText(characterClass.name);
        raceLabel.setText(race.name);

        maxPVLabel.setText(string(attribute.getConstitution() + integer(characterClass.dicePv.split("d")[1])));
        currentPVLabel.setText(maxPVLabel.getText());
    }

    private String string(int i){
        return String.valueOf(i);
    }

    private int integer(String s){
        return Integer.parseInt(s);
    }

    public void createCharacterButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

    public enum Alignment {
        LAWFUL(1, "Ordeiro"),
        CHAOTIC(3, "Caos"),
        NEUTRAL(2, "Neutro");

        private final int id;
        private final String text;

        Alignment(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public static String getTextForId(int id) {
            for (Alignment alignment : values()) {
                if (alignment.getId() == id) {
                    return alignment.getText();
                }
            }
            return null; // Or throw an exception if ID is not found.
        }

        public static Alignment setAlignment(int id) {
            switch (id) {
                case 1:
                    return Alignment.LAWFUL;
                case 2:
                    return Alignment.NEUTRAL;
                case 3:
                    return Alignment.CHAOTIC;
                default:
                    throw new IllegalArgumentException("ID de alinhamento inválido: " + id);
            }
        }

    }

    public enum Race {
        HUMAN("Humano", 30, "M"),
        ELF("Elfo", 35, "M"),
        DWARF("Anão", 25, "S"),
        HALFLING("Halfling", 25, "S");

        private final String name;
        private final int movement;
        private final String size;

        Race(String name, int movement, String size) {
            this.name = name;
            this.movement = movement;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public int getMovement() {
            return movement;
        }

        public String getSize() {
            return size;
        }

        public static String getTextForId(int id) {
            if (id < 1 || id > values().length) {
                throw new IllegalArgumentException("ID da raça inválido.");
            }
            return values()[id - 1].getName();
        }

        public static Race setRace(int id) {
            switch (id) {
                case 1:
                    return Race.HUMAN;
                case 2:
                    return Race.ELF;
                case 3:
                    return Race.DWARF;
                case 4:
                    return Race.HALFLING;
                default:
                    throw new IllegalArgumentException("ID de raça inválido: " + id);
            }
        }
    }


    public enum CharacterClass {
        WARRIOR(1, "Guerreiro", "1d10"),
        WIZARD(2, "Mago", "1d4"),
        ROGUE(3, "Ladrão", "1d6"),
        CLERIC(4, "Clérigo", "1d8");

        private final int id;
        private final String name;
        private final String dicePv;

        CharacterClass(int id, String name, String dicePv) {
            this.id = id;
            this.name = name;
            this.dicePv = dicePv;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDicePv() {
            return dicePv;
        }

        public static String getTextForId(int id) {
            for (CharacterClass characterClass : values()) {
                if (characterClass.getId() == id) {
                    return characterClass.getName();
                }
            }
            return null; // Or throw an exception if ID is not found.
        }

        public static CharacterClass setCharacterClass(int id) {
            switch (id) {
                case 1:
                    return CharacterClass.WARRIOR;
                case 2:
                    return CharacterClass.WIZARD;
                case 3:
                    return CharacterClass.ROGUE;
                case 4:
                    return CharacterClass.CLERIC;
                default:
                    throw new IllegalArgumentException("ID de classe inválido: " + id);
            }
        }
    }

}
