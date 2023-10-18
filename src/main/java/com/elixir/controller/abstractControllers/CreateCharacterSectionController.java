package com.elixir.controller.abstractControllers;

import com.elixir.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
