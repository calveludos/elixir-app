package com.elixir.controller.abstractControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public abstract class CreateCharacterSectionController extends MenuController {

    @FXML
    public void dateSectionButtonAction(ActionEvent event){
        if (((Button) event.getSource()).getStyle().contains("-fx-background-color: TRASPARENT")){
            saveCharacter("createCharacterDatePane");
        }
    }

    @FXML
    public void attributeSectionButtonAction(ActionEvent event){
        if (((Button) event.getSource()).getStyle().contains("-fx-background-color: TRASPARENT")){
            saveCharacter("createCharacterAttributesPane");
        }
    }

    @FXML
    public void raceSectionButtonAction(ActionEvent event){
        if (((Button) event.getSource()).getStyle().contains("-fx-background-color: TRASPARENT")){
            saveCharacter("createCharacterRacePane");
        }
    }

    @FXML
    public void classSectionButtonAction(ActionEvent event){
        if (((Button) event.getSource()).getStyle().contains("-fx-background-color: TRASPARENT")){
            saveCharacter("createCharacterClassPane");
        }
    }

    @FXML
    public void backgroundSectionButtonAction(ActionEvent event){
        if (((Button) event.getSource()).getStyle().contains("-fx-background-color: TRASPARENT")){
            saveCharacter("createCharacterBackgroundPane");
        }
    }

    protected abstract void saveCharacter(String fxml);

}
