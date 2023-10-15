package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import com.elixir.dao.AttributeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Map;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Filter;


public class CreateCharacterRaceController extends CreateCharacterSectionController {
    @FXML
    private Label chosenRaceLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label showUpdateLabel;
    private Character character;
    private Attribute attribute;
    private Attribute copyAttribute;



    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");
        attribute = (Attribute) reader.getObject("attribute");

        try{
            if(character.getRaceId() > 0){
                switch (character.getRaceId()){
                    case 1:
                        chosenRaceLabel.setText("Humano");
                        break;
                    case 2:
                        chosenRaceLabel.setText("Elfo");
                        break;
                    case 3:
                        chosenRaceLabel.setText("Anão");
                        break;
                    case 4:
                        chosenRaceLabel.setText("Halfing");
                        break;
                    default:
                        chosenRaceLabel.setText("");
                        break;
                }
            }
        } catch (NullPointerException e){
            character = new Character();
        }
    }

    @FXML
    void choiseDwarfButtonAction(ActionEvent event) {
        copyAttribute = new Attribute(
                attribute.getStrength(),
                attribute.getDexterity(),
                attribute.getCharisma(),
                attribute.getIntelligence(),
                attribute.getWisdom(),
                attribute.getConstitution()
        );
        copyAttribute.setConstitution(copyAttribute.getConstitution()+2);
        copyAttribute.setCharisma(copyAttribute.getCharisma()-2);
        chosenRaceLabel.setText("Anão");
        messageLabel.setText("Bônus de anão adicionado: +2 em constituição e -2 em carisma");
        showUpdateLabel.setText("Novos valores em constituição: " + copyAttribute.getConstitution() + " e carisma: " + copyAttribute.getCharisma());
        character.setRaceId(3);
        System.out.println(copyAttribute);

    }



    @FXML
    void choiseElfButtonAction(ActionEvent event) {
        copyAttribute = new Attribute(
                attribute.getStrength(),
                attribute.getDexterity(),
                attribute.getCharisma(),
                attribute.getIntelligence(),
                attribute.getWisdom(),
                attribute.getConstitution()
        );
        copyAttribute.setDexterity(copyAttribute.getDexterity()+2);
        copyAttribute.setConstitution(copyAttribute.getConstitution()-2);
        chosenRaceLabel.setText("Elfo");
        messageLabel.setText("Bônus de elfo adicionado: +2 em destreza e -2 em constituição");
        showUpdateLabel.setText("Novos valores em destreza: " + copyAttribute.getDexterity() + " e constituição: " + copyAttribute.getConstitution());
        character.setRaceId(2);
        System.out.println(copyAttribute);

    }
    @FXML
    void choiseHalflingButtonAction(ActionEvent event) {
        copyAttribute = new Attribute(
                attribute.getStrength(),
                attribute.getDexterity(),
                attribute.getCharisma(),
                attribute.getIntelligence(),
                attribute.getWisdom(),
                attribute.getConstitution()
        );
        copyAttribute.setDexterity(copyAttribute.getDexterity()+2);
        copyAttribute.setStrength(copyAttribute.getStrength()-2);
        chosenRaceLabel.setText("Halfing");
        messageLabel.setText("Bônus de halfing adicionado: +2 em destreza e -2 em força");
        showUpdateLabel.setText("Novos valores em constituição: " + copyAttribute.getDexterity() + " e carisma: " + copyAttribute.getStrength());
        System.out.println(copyAttribute);
        character.setRaceId(4);
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

        if (copyAttribute != null) {
            attribute.setStrength(copyAttribute.getStrength());
            attribute.setDexterity(copyAttribute.getDexterity());
            attribute.setIntelligence(copyAttribute.getIntelligence());
            attribute.setWisdom(copyAttribute.getWisdom());
            attribute.setCharisma(copyAttribute.getCharisma());
            attribute.setConstitution(copyAttribute.getConstitution());
        }
        saver.saveObject("character", character);
        saver.saveObject("attribute", attribute);
        PaneManager paneManager = new PaneManager();
        paneManager.openPane(fxml);
    }

}
