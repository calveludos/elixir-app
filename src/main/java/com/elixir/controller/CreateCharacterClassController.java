package com.elixir.controller;

import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class CreateCharacterClassController {

    @FXML
    private TextArea clerigoTextArea;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextArea hdaTextArea;

    @FXML
    private TextArea ladraoTextArea;

    @FXML
    private TextArea magoTextArea;

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

    @FXML
    public void backgroundCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterBackgroundPane");
    }
    @FXML
    public void raceCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterRacePane");
    }

    @FXML
    public void createCharacterButtonAction(ActionEvent event) {
        saveCharacter("newCharacterPane");
    }

    @FXML
    public void attributesCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    @FXML
    public void dateCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterDatePane");
    }

    @FXML
    public void nextClassButtonAction(ActionEvent event) {
        saveCharacter("createCharacterBackgroundPane");
    }

    @FXML
    public void classCharacterButtonAction(ActionEvent event) {
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

    public void saveCharacter(String fxml) {
        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }
}
