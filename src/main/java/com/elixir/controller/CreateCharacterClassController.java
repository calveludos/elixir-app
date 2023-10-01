package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateCharacterClassController extends CreateCharacterSectionController {
    @FXML
    private Label chosenClassLabel;

    private Character character;

    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");

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
        } catch (NullPointerException e) {
            character = new Character();
        }
    }

    @FXML
    void choiseClerigoButtonAction(ActionEvent event) {
        chosenClassLabel.setText("Clérigo");
        character.setClassId(4);
    }

    @FXML
    void choiseHDAButtonAction(ActionEvent event) {
        chosenClassLabel.setText("Homem de Armas");
        character.setClassId(1);
    }

    @FXML
    void choiseLadraoButtonAction(ActionEvent event) {
        chosenClassLabel.setText("Ladrão");
        character.setClassId(3);
    }

    @FXML
    void choiseMagoButtonAction(ActionEvent event) {
        chosenClassLabel.setText("Mago");
        character.setClassId(2);
    }

    @Override
    protected void saveCharacter(String fxml) {
        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager((Stage) chosenClassLabel.getScene().getWindow());
        paneManager.openPane(fxml);
    }
}
