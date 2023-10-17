package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.lang.Math;
import java.util.Objects;

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
    private TextField diceBonus;

    @FXML
    public void initialize() {
        super.initialize();

        ObservableList<Integer> diceOptions = FXCollections.observableArrayList(4, 6, 8, 12, 20);
        diceType.getItems().addAll(diceOptions);

        SpinnerValueFactory<Integer> valueFactoryAmount = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        diceAmount.setValueFactory(valueFactoryAmount);
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

        diceBonus.textProperty().addListener((object, oldValue, newValue) -> {
            if (newValue.length() > 100 || !newValue.matches("-?\\d*")) {
                diceBonus.textProperty().setValue(oldValue);
            } else {
                diceBonus.textProperty().setValue(newValue);
            }
        });



    }

    @FXML
    public void rollDiceButtonOnAction(ActionEvent event) {
        diceValue.setTextFill(Color.BLACK);
        int total = 0;
        double randomNumber = Math.random();
        Integer selectedValueDiceType = diceType.getValue();
        int numberOfRolls = diceAmount.getValue();

        if(Objects.equals(diceBonus.getText(), "")){diceBonus.setText("0");}

        for (int i = 0; i < numberOfRolls; i++) {
            int diceResult = (int) (1 + Math.random() * selectedValueDiceType);
            total += diceResult + Integer.parseInt(diceBonus.getText());
        }

        System.out.println(total);
        if(total <= 1){
            diceValue.setTextFill(Color.RED);
        }
        else if(total >= selectedValueDiceType){
            diceValue.setTextFill(Color.GREEN);
        }
        diceValue.setText(Integer.toString(total));

    }

}