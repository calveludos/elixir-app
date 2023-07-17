package com.elixir;

import com.elixir.model.Race;
import com.elixir.model.Class;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CreateCharacterClassController {

    @FXML
    private Button attributesCharacterButton;

    @FXML
    private Button backgroundCharacterButton;

    @FXML
    private Button choiseClerigoButton;

    @FXML
    private Button choiseHDAButton;

    @FXML
    private Button choiseLadraoButton;

    @FXML
    private Button choiseMagoButton;

    @FXML
    private Label chosenclassLabel;

    @FXML
    private Button classCharacterButton;

    @FXML
    private MenuButton classMenuButton;

    @FXML
    private TitledPane clerigoAccordion;

    @FXML
    private TextArea clerigoTextArea;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Button dateCharacterButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TitledPane hdaAccordion;

    @FXML
    private TextArea hdaTextArea;

    @FXML
    private TitledPane ladraoAccordion;

    @FXML
    private TextArea ladraoTextArea;

    @FXML
    private TitledPane magoAccordion;

    @FXML
    private TextArea magoTextArea;

    @FXML
    private Button raceCharacterButton;

    private static Class classe;



    @FXML
    void attributesCharacterButtonAction(ActionEvent event) {

    }

    @FXML
    void backgroundCharacterButtonAction(ActionEvent event) {

    }

    @FXML
    void choiseClerigoButtonAction(ActionEvent event) {
        chosenclassLabel.setText("Clérigo");
    }

    @FXML
    void choiseHDAButtonAction(ActionEvent event) {
        chosenclassLabel.setText("Homem de armas");
    }


    @FXML
    void choiseLadraoButtonAction(ActionEvent event) {
        chosenclassLabel.setText("Ladrão");
    }


    @FXML
    void choiseMagoButtonAction(ActionEvent event) {
        chosenclassLabel.setText("Mago");
    }


    @FXML
    void chooseCleric(ActionEvent event) {

    }

    @FXML
    void chooseThief(ActionEvent event) {

    }

    @FXML
    void chooseWarrior(ActionEvent event) {

    }

    @FXML
    void chooseWizard(ActionEvent event) {

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

    private void saveCharcter(String fxml){
        Class classe = new Class();
            try {
                classe.setName(chosenclassLabel.getText());
        } catch (IllegalArgumentException e){
            errorLabel.setText("ERRO! " + e.getMessage());
            return;
        }
            this.classe =classe;


        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
            paneManager.openPane(fxml);
    }

    public static Class getClasse() { return classe;} // Se colocar getClass vai puxar o metodo :(

}
