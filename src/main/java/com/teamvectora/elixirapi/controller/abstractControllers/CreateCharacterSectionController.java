package com.teamvectora.elixirapi.controller.abstractControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class CreateCharacterSectionController extends MenuController {

    @FXML
    public void initialize(){
        super.initialize();
    }


    @FXML
    public void dateSectionButtonAction(ActionEvent event){
            saveCharacter("createCharacterDatePane");
    }

    @FXML
    public void attributeSectionButtonAction(ActionEvent event){
            saveCharacter("createCharacterAttributesPane");
    }

    @FXML
    public void raceSectionButtonAction(ActionEvent event){
            saveCharacter("createCharacterRacePane");
    }

    @FXML
    public void classSectionButtonAction(ActionEvent event){
            saveCharacter("createCharacterClassPane");
    }

    @FXML
    public void backgroundSectionButtonAction(ActionEvent event){
            saveCharacter("createCharacterBackgroundPane");
    }

    protected abstract void saveCharacter(String fxml);

}
