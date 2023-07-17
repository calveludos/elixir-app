package com.elixir;

import com.elixir.model.Character;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import com.elixir.model.Race;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

import java.util.Objects;

public class CreateCharacterRaceController {

    @FXML
    private Button attributesCharacterButton;

    @FXML
    private Label chosenraceLabel;

    @FXML
    private Button backgroundCharacterButton;

    @FXML
    private Button choiseDwarfButton;

    @FXML
    private Button choiseElfButton;

    @FXML
    private Button choiseHalflingButton;

    @FXML
    private Button choiseHumanButton;

    @FXML
    private Rectangle chosenraceField;

    @FXML
    private Button classCharacterButton;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button dateCharacterButton;

    @FXML
    private TitledPane dwarfAccordion;

    @FXML
    private MenuItem dwarfMenuItem;

    @FXML
    private TextArea dwarfTextArea;

    @FXML
    private TitledPane elfAccordion;

    @FXML
    private MenuItem elfMenuItem;

    @FXML
    private TextArea elfTextArea;

    @FXML
    private Label errorLabel;

    @FXML
    private TitledPane halflingAccordion;

    @FXML
    private MenuItem halflingMenuItem;

    @FXML
    private TextArea halflingTextArea;

    @FXML
    private TitledPane humanAccordion;

    @FXML
    private MenuItem humanMenuItem;

    @FXML
    private TextArea humanTextArea;

    @FXML
    private Button raceCharacterButton;

    @FXML
    private MenuButton raceMenuButton;

    private static  Race race;


    @FXML
    private void initialize(){
    }



    @FXML
    void attributesCharacterButtonAction(ActionEvent event) {

    }

    @FXML
    void backgroundCharacterButtonAction(ActionEvent event)  {
        saveCharacter("createCharacterBackgroundPane");
    }

    @FXML
    void choiseDwarfButtonAction(ActionEvent event) {
        chosenraceLabel.setText("Anão");
    }


    @FXML
    void choiseElfButtonAction(ActionEvent event) {
        chosenraceLabel.setText("Elfo");

    }

    @FXML
    void choiseHalflingButtonAction(ActionEvent event) {
        chosenraceLabel.setText("Halfing");

    }

    @FXML
    void choiseHumanButtonAction(ActionEvent event) {
        chosenraceLabel.setText("Humano");
    }

    @FXML
    void chooseDwarf(ActionEvent event) {

    }

    @FXML
    void chooseElf(ActionEvent event) {

    }

    @FXML
    void chooseHalfling(ActionEvent event) {

    }

    @FXML
    void chooseHuman(ActionEvent event) {

    }

    @FXML
    void classCharacterButtonAction(ActionEvent event) {

    }

    @FXML
    void createCharacterButtonAction(ActionEvent event) {

    }

    @FXML
    void dateCharacterButtonAction(ActionEvent event) {

    }

    @FXML
    void raceCharacterButtonAction(ActionEvent event) {

    }


   private void saveCharacter(String fxml){
        Race race = new Race();
        try {
            race.setName(chosenraceLabel.getText());
        } catch (IllegalArgumentException e){
            errorLabel.setText("ERRO! " + e.getMessage());
            return;
        }
        this.race =race;

       System.out.println(race.toString());

       PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }

   public static Race getRace() { return race;}

}
