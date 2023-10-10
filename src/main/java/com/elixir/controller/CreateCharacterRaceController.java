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
        attribute.setCharisma(attribute.getCharisma()-2);
        attribute.setConstitution(attribute.getConstitution()+2);
        System.out.println(attribute.getConstitution());
        chosenRaceLabel.setText("Anão");
        messageLabel.setText("Bônus de anão adicionado: +2 em constituição e -2 em carisma");
        showUpdateLabel.setText("Novos valores em constituição: " + attribute.getConstitution() + " e carisma: " + attribute.getCharisma());
        character.setRaceId(3);
    }



    @FXML
    void choiseElfButtonAction(ActionEvent event) {
        attribute.setCharisma(attribute.getDexterity()-2);
        attribute.setConstitution(attribute.getConstitution()+2);
        chosenRaceLabel.setText("Elfo");
        messageLabel.setText("Bônus de elfo adicionado: +2 em destreza e -2 em constituição");
        showUpdateLabel.setText("Novos valores em destreza: " + attribute.getDexterity() + " e constituição: " + attribute.getConstitution());
        character.setRaceId(2);
    }

    @FXML
    void choiseHalflingButtonAction(ActionEvent event) {
        attribute.setCharisma(attribute.getStrength()-2);
        attribute.setConstitution(attribute.getDexterity()+2);
        chosenRaceLabel.setText("Halfing");
        messageLabel.setText("Bônus de halfing adicionado: +2 em destreza e -2 em força");
        showUpdateLabel.setText("Novos valores em constituição: " + attribute.getDexterity() + " e carisma: " + attribute.getStrength());

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
        saver.saveObject("character", character);
        saver.saveObject("attribute", attribute);

        PaneManager paneManager = new PaneManager((Stage) chosenRaceLabel.getScene().getWindow());
        paneManager.openPane(fxml);
    }

}
