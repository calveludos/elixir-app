package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.lang.Math;

public class rollDiceController extends MenuController {

    @FXML
    private ChoiceBox<Integer> diceType;

    @FXML
    private ImageView diceImage;
    @FXML
    private Spinner<Integer> diceAmount;

    @FXML
    private Button rollDiceButton;

    @FXML
    private Label diceValue;

    @FXML
    private void initialize() {
        ObservableList<Integer> options = FXCollections.observableArrayList(4, 6, 8, 12, 20);
        diceType.setItems(options);
       /* diceType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue == 4) {
                    Image newImage = new Image("elixir-api\\src\\main\\resources\\media\\d4.png");
                    diceImage.setImage(newImage);
                }
            }
        });*/

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        diceAmount.setValueFactory(valueFactory);
        diceAmount.setEditable(true);

        diceAmount.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                diceAmount.getEditor().setText(oldValue);
            } else {
                int value = 1;
                try {
                    value = Integer.parseInt(newValue);
                } catch (Exception ignored) {
                }
                if (value < 1 || value > 20) {
                    diceAmount.getEditor().setText(oldValue);
                }
            }
        });
    }

    @FXML
    void rollDiceButton (ActionEvent event){
        Integer selectedValue = diceType.getValue();
        int diceOutput = (int) (Math.random() * selectedValue);
        diceValue.setText(Integer.toString(diceOutput));
    }

}