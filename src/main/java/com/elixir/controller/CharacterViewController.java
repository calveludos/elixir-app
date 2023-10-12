package com.elixir.controller;

import com.elixir.manager.ObjectSaveManager;
import com.elixir.model.tables.CharacterAttributes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.elixir.model.Character;
import com.elixir.model.Attribute;
import com.elixir.manager.*;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Random;

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
    private Label dexAjustLabel;

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
    private Label hideInDarkLabel;

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
    private Label moveOpenLocksLabel;

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
    private Label strAjustLabel;

    @FXML
    private Label strLabel;

    @FXML
    private Label surpriseAjustLabel;

    @FXML
    private HBox topMenu;

    @FXML
    private Label trapAjustLabel;

    @FXML
    private Label wisJPLabel;

    @FXML
    private Label wisLabel;

    @FXML
    private void initialize() {
        ObjectSaveManager reader = new ObjectSaveManager();

        Character character = (Character) reader.getObject("character");
        Attribute attribute = (Attribute) reader.getObject("attribute");

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

        //Preencher os campos de subatributos com base nos valores dos atributos e subatributos
        CharacterAttributes.Strength strengthSubAttributes = new CharacterAttributes.Strength();
        CharacterAttributes.Dexterity dexteritySubAttributes = new CharacterAttributes.Dexterity();
        CharacterAttributes.Constitution constitutionSubAttributes = new CharacterAttributes.Constitution();
        CharacterAttributes.Wisdom wisdomSubAttributes = new CharacterAttributes.Wisdom();
        CharacterAttributes.Intelligence intelligenceSubAttributes = new CharacterAttributes.Intelligence();
        CharacterAttributes.Charisma charismSubAttributes = new CharacterAttributes.Charisma();

        // Força (Strength)
        strAjustLabel.setText(String.valueOf(strengthSubAttributes.getStrength(attribute.getStrength())));

        // Destreza (Dexterity)
        dexAjustLabel.setText(String.valueOf(dexteritySubAttributes.getTableDexterity(attribute.getDexterity()).get("Ajuste de ataque e surpresa")));
        hideInDarkLabel.setText(String.valueOf(dexteritySubAttributes.getTableDexterity(attribute.getDexterity()).get("Esconder-se nas sombras")));
        trapAjustLabel.setText(String.valueOf(dexteritySubAttributes.getTableDexterity(attribute.getDexterity()).get("Localizar e desarmar")));
        moveOpenLocksLabel.setText(String.valueOf(dexteritySubAttributes.getTableDexterity(attribute.getDexterity()).get("Mover-se e abrir fechaduras")));

        // Constituição (Constitution)
        pvAjustLabel.setText(String.valueOf(constitutionSubAttributes.getTableConstitution(attribute.getConstitution()).get("Ajuste de Pontos de Vida")));
        protectionConAjustLabel.setText(pvAjustLabel.getText());
        resurrectionLabel.setText(String.valueOf(constitutionSubAttributes.getTableConstitution(attribute.getConstitution()).get("Chance de Ressurreição")));

        // Sabedoria (Wisdom)
        protectionWisAjustLabel.setText(String.valueOf(wisdomSubAttributes.getTableWisdom(attribute.getWisdom()).get("Ajuste de proteção")));
        // Preencher as informações sobre magias divinas adicionais, caso exista, para o atributo Sabedoria
        Map<String, Object> wisdomMap = wisdomSubAttributes.getTableWisdom(attribute.getWisdom());
        if (wisdomMap != null) {
            int totalDivineMagic = (int) wisdomMap.get("Total Magias divinas adicionais");
            divineMagicLabel.setText(string(totalDivineMagic));
        } else {
            divineMagicLabel.setText("N/A");
        }


        // Inteligência (Intelligence)
        languagesLabel.setText(String.valueOf(intelligenceSubAttributes.getTableIntelligence(attribute.getIntelligence()).get("Idioma")));
        learnMagicLabel.setText(String.valueOf(intelligenceSubAttributes.getTableIntelligence(attribute.getIntelligence()).get("Chance de aprender magias")) + "%");

        // Preencher as informações sobre magias adicionais, caso exista, para o atributo Inteligência
        Map<String, Object> intelligenceMap = intelligenceSubAttributes.getTableIntelligence(attribute.getIntelligence());
        if (intelligenceMap != null) {
            Map<String, Integer> arcaneMagicMap = (Map<String, Integer>) intelligenceMap.get("Magias adicionais");
            if (arcaneMagicMap != null) {
                int totalArcaneMagic = (int) intelligenceMap.get("Total de magias adicionais");
                arcaneMagicLabel.setText(string(totalArcaneMagic));
            } else {
                arcaneMagicLabel.setText("N/A");
            }
        } else {
            arcaneMagicLabel.setText("N/A");
        }

        // Carisma (Charism)
        followLabel.setText(String.valueOf(charismSubAttributes.getTableCharism(attribute.getCharisma()).get("Número máximo de seguidores")));
        reactionAjustLabel.setText(String.valueOf(charismSubAttributes.getTableCharism(attribute.getCharisma()).get("Ajuste de reação")));
        monsterLabel.setText(String.valueOf(charismSubAttributes.getTableCharism(attribute.getCharisma()).get("Mortos-vivos afastados")));

        dexCALabel.setText(dexAjustLabel.getText());
        armorCALabel.setText("-");
        bonusCALabel.setText(string(character.getClassArmorBonus()));

        Random random = new Random();
        goldLabel.setText(string((random.nextInt(24 - 3 + 1) + 3)*10));
    }

    private String string(int i){
        return String.valueOf(i);
    }

    private int integer(String s){
        return Integer.parseInt(s);
    }

    public void createCharacterButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("myCharactersPane");
    }
}
