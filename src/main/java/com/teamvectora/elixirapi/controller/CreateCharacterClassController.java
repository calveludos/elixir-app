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

        try{
            if(character.getClassId() > 0){
                switch (character.getClassId()){
                    case 1:
                        chosenClassLabel.setText("Homem de Armas");
                        break;
                    case 2:
                        chosenClassLabel.setText("Mago");
                        break;
                    case 3:
                        chosenClassLabel.setText("Ladrão");
                        break;
                    case 4:
                        chosenClassLabel.setText("Clérigo");
                        break;
                    default:
                        chosenClassLabel.setText("");
                        break;
                }
            }
            attribute = character.getAttribute();
        } catch (NullPointerException e) {
            character = new CharacterMaster();
            character.setAttribute(new Attribute(10, 10, 10, 10, 10, 10));
            attribute = character.getAttribute();
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
