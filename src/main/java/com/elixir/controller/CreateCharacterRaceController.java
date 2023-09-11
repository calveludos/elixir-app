package com.elixir.controller;

import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CreateCharacterRaceController {
    @FXML
    private Label chosenRaceLabel;

    @FXML
    private Button createCharacterButton;

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
    void backgroundCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterBackgroundPane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

    @FXML
    void classCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterClassPane");
    }

    @FXML
    void createCharacterButtonAction(ActionEvent event) {
        saveCharacter("newCharacterPane");
    }

    @FXML
    void attributesCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    @FXML
    void dateCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterDatePane");
    }

    @FXML
    void nextRaceButtonAction(ActionEvent event) {
        saveCharacter("createCharacterClassPane");
    }

    @FXML
    public void raceCharacterButtonAction(ActionEvent event) {}

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

    private void saveCharacter(String fxml){
        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }

    public Character setCharacter() {
        return character;
    }
    public void setCharacter(Character character){
        this.character = character;
    }

}
