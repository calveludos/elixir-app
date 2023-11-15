package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.CreateCharacterSectionController;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.Attribute;
import com.teamvectora.elixirapi.model.CharacterMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CreateCharacterClassController extends CreateCharacterSectionController {
    @FXML
    private Label chosenClassLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label messageLabel1;

    private CharacterMaster character;
    private Attribute attribute;

    @FXML
    public void initialize(){
        super.initialize();

        ObjectSaveManager reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");
        attribute = new Attribute(character.getAttribute().getStrength(),
                character.getAttribute().getDexterity(),
                character.getAttribute().getConstitution(),
                character.getAttribute().getIntelligence(),
                character.getAttribute().getWisdom(),
                character.getAttribute().getCharisma());

        if (reader.getObject("bonus") != null){
            System.out.println("Soma bonus");
            Attribute bonusAttribute = (Attribute) reader.getObject("bonus");
            attribute.setStrength(attribute.getStrength() + bonusAttribute.getStrength());
            attribute.setDexterity(attribute.getDexterity() + bonusAttribute.getDexterity());
            attribute.setConstitution(attribute.getConstitution() + bonusAttribute.getConstitution());
            attribute.setIntelligence(attribute.getIntelligence() + bonusAttribute.getIntelligence());
            attribute.setWisdom(attribute.getWisdom() + bonusAttribute.getWisdom());
            attribute.setCharisma(attribute.getCharisma() + bonusAttribute.getCharisma());
        }

        System.out.println(attribute);

        if(character.getClassId() > 0){
            switch (character.getClassId()) {
                case 1 -> chosenClassLabel.setText("Homem de Armas");
                case 2 -> chosenClassLabel.setText("Mago");
                case 3 -> chosenClassLabel.setText("Ladrão");
                case 4 -> chosenClassLabel.setText("Clérigo");
                default -> chosenClassLabel.setText("");
            }
        }
    }

    @FXML
    void choiseClerigoButtonAction(ActionEvent event) {
        //con 12
        //wis 14
        if (attribute.getConstitution() < 12 || attribute.getWisdom() < 14){
            messageLabel.setText("Atributos mínimos para clérigo: 12 constituição e 14 sabedoria");
            messageLabel1.setText("Seus atributos: " + attribute.getConstitution()+ " constituição e " + attribute.getWisdom() + " sabedoria");
            return;
        }
        chosenClassLabel.setText("Clérigo");
        character.setClassId(4);
    }

    @FXML
    void choiseHDAButtonAction(ActionEvent event) {
        //str 12
        //con 12
        if (attribute.getConstitution() < 12 || attribute.getStrength() < 12){
            messageLabel.setText("Atributos mínimos para clérigo: 12 constituição e 12 força");
            messageLabel1.setText("Seus atributos: " + attribute.getStrength()+ " força e " + attribute.getConstitution() + " constituição");
            return;
        }
        chosenClassLabel.setText("Homem de Armas");
        character.setClassId(1);
    }

    @FXML
    void choiseLadraoButtonAction(ActionEvent event) {
        //dex 12
        if (attribute.getDexterity() < 12){
            messageLabel.setText("Atributos mínimos para clérigo: 12 destreza");
            messageLabel1.setText("Seus atributos: " + attribute.getDexterity()+ " destreza ");
            return;
        }
        chosenClassLabel.setText("Ladrão");
        character.setClassId(3);
    }

    @FXML
    void choiseMagoButtonAction(ActionEvent event) {
        //int 14
        if (attribute.getIntelligence() < 14){
            messageLabel.setText("Atributos mínimos para clérigo: 14 inteligencia");
            messageLabel1.setText("Seus atributos: " + attribute.getIntelligence()+ " inteligencia ");
            return;
        }
        chosenClassLabel.setText("Mago");
        character.setClassId(2);
    }

    @Override
    protected void saveCharacter(String fxml) {
        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);
    }
}
