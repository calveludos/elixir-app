package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.CreateCharacterSectionController;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.Attribute;
import com.teamvectora.elixirapi.model.CharacterMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CreateCharacterRaceController extends CreateCharacterSectionController {
    @FXML
    private Label chosenRaceLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label showUpdateLabel;
    private CharacterMaster character;
    private Attribute attribute;
    private Attribute bonusAttribute;

    @FXML
    public void initialize(){
        super.initialize();

        ObjectSaveManager reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");
        attribute = character.getAttribute();

        if(character.getRaceId() > 0){
            switch (character.getRaceId()) {
                case 1 -> chosenRaceLabel.setText("Humano");
                case 2 -> chosenRaceLabel.setText("Elfo");
                case 3 -> chosenRaceLabel.setText("Anão");
                case 4 -> chosenRaceLabel.setText("Halfing");
                default -> chosenRaceLabel.setText("");
            }
        }
    }

    @FXML
    void choiseDwarfButtonAction(ActionEvent event) {
        bonusAttribute = new Attribute();
        bonusAttribute.setConstitution(bonusAttribute.getConstitution()+2);
        bonusAttribute.setCharisma(bonusAttribute.getCharisma()-2);
        chosenRaceLabel.setText("Anão");
        messageLabel.setText("Bônus de anão adicionado: +2 em constituição e -2 em carisma");
        showUpdateLabel.setText("Novos valores em constituição: " + bonusAttribute.getConstitution() + " e carisma: " + bonusAttribute.getCharisma());
        character.setRaceId(3);
        System.out.println(bonusAttribute);
        bonusAttribute.setConstitution(+2);
        bonusAttribute.setCharisma(-2);

    }

    @FXML
    void choiseElfButtonAction(ActionEvent event) {
        bonusAttribute = new Attribute();
        bonusAttribute.setDexterity(bonusAttribute.getDexterity()+2);
        bonusAttribute.setConstitution(bonusAttribute.getConstitution()-2);
        chosenRaceLabel.setText("Elfo");
        messageLabel.setText("Bônus de elfo adicionado: +2 em destreza e -2 em constituição");
        showUpdateLabel.setText("Novos valores em destreza: " + bonusAttribute.getDexterity() + " e constituição: " + bonusAttribute.getConstitution());
        character.setRaceId(2);
        System.out.println(bonusAttribute);

        bonusAttribute.setDexterity(+2);
        bonusAttribute.setConstitution(-2);

    }
    @FXML
    void choiseHalflingButtonAction(ActionEvent event) {
        bonusAttribute = new Attribute();
        bonusAttribute.setDexterity(bonusAttribute.getDexterity()+2);
        bonusAttribute.setStrength(bonusAttribute.getStrength()-2);
        chosenRaceLabel.setText("Halfing");
        messageLabel.setText("Bônus de halfing adicionado: +2 em destreza e -2 em força");
        showUpdateLabel.setText("Novos valores em constituição: " + bonusAttribute.getDexterity() + " e carisma: " + bonusAttribute.getStrength());
        System.out.println(bonusAttribute);
        character.setRaceId(4);

        bonusAttribute.setDexterity(+2);
        bonusAttribute.setStrength(-2);
    }
    @FXML
    void choiseHumanButtonAction(ActionEvent event) {
        chosenRaceLabel.setText("Humano");
        messageLabel.setText("");
        showUpdateLabel.setText("");
        character.setRaceId(1);
    }

    @Override
    protected void saveCharacter(String fxml){
        ObjectSaveManager saver = new ObjectSaveManager();

        if (bonusAttribute != null) {
            saver.saveObject("bonus", bonusAttribute);
        }
        character.setAttribute(attribute);
        saver.saveObject("character", character);
        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);
    }

}
