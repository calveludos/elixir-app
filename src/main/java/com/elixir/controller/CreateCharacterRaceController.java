package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateCharacterRaceController extends CreateCharacterSectionController {
    @FXML
    private Label chosenRaceLabel;

    private Character character;

    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");

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
        chosenRaceLabel.setText("Anão");
        character.setRaceId(3);
    }
    @FXML
    void choiseElfButtonAction(ActionEvent event) {
        chosenRaceLabel.setText("Elfo");
        character.setRaceId(2);
    }
    @FXML
    void choiseHalflingButtonAction(ActionEvent event) {
        chosenRaceLabel.setText("Halfing");
        character.setRaceId(4);
    }
    @FXML
    void choiseHumanButtonAction(ActionEvent event) {
        chosenRaceLabel.setText("Humano");
        character.setRaceId(1);
    }

    @Override
    protected void saveCharacter(String fxml){
        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager((Stage) chosenRaceLabel.getScene().getWindow());
        paneManager.openPane(fxml);
    }

}
