package com.elixir.controller;

import com.elixir.controller.abstractControllers.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.lang.Math;
import java.util.Objects;

public class RollDiceController extends MenuController {

    public HBox HBoxDiceValue;
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

        ObservableList<Integer> diceOptions = FXCollections.observableArrayList(4, 6, 8, 12, 20, 100);
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
        if (HBoxDiceValue.getChildren().size() > 1){
            HBoxDiceValue.getChildren().clear();
        }

        diceValue.setTextFill(Color.BLACK);
        int total = 0;

        Integer selectedValueDiceType = diceType.getValue();

        diceImage.setImage(new Image("/media/d" + selectedValueDiceType + ".png"));

        int numberOfRolls = diceAmount.getValue();

        if(diceBonus.getText().isEmpty() || diceBonus.getText().isBlank()){diceBonus.setText("0");}
        int numDiceBonus = Integer.parseInt(diceBonus.getText());

        for (int i = 0; i < numberOfRolls; i++) {
            int diceResult = (int) (1 + Math.random() * selectedValueDiceType);
            total += diceResult + Integer.parseInt(diceBonus.getText());

            if (numDiceBonus == 0 && numberOfRolls == 1)
                break;

            Label diceLabel = new Label();
            diceLabel.setFont(new Font("System Bold", 19.0));
            if (diceResult == 1) {
                diceLabel.setTextFill(Color.RED);
            } else if (diceResult == selectedValueDiceType) {
                diceLabel.setTextFill(Color.GREEN);
            }
            diceLabel.setText(Integer.toString(diceResult));
            HBoxDiceValue.getChildren().add(diceLabel);

            if (i != numberOfRolls - 1) {
                Label plusLabel = new Label();
                plusLabel.setFont(new Font("System Bold", 19.0));
                plusLabel.setTextFill(Color.BLACK);
                plusLabel.setText("+");
                HBoxDiceValue.getChildren().add(plusLabel);
            }

        }


        if (numDiceBonus != 0 || numberOfRolls > 1){

            Label equalsLabel = new Label();
            equalsLabel.setFont(new Font("System Bold", 19.0));
            equalsLabel.setTextFill(Color.BLACK);
            if (numDiceBonus > 0){
                Label bonusLabel = new Label();
                bonusLabel.setFont(new Font("System Bold", 19.0));
                bonusLabel.setTextFill(Color.GREEN);
                bonusLabel.setText(" (+" + numDiceBonus + ")");
                HBoxDiceValue.getChildren().add(bonusLabel);
            } else if (numDiceBonus < 0){
                Label bonusLabel = new Label();
                bonusLabel.setFont(new Font("System Bold", 19.0));
                bonusLabel.setTextFill(Color.RED);
                bonusLabel.setText(" (+" + numDiceBonus + ")");
                HBoxDiceValue.getChildren().add(bonusLabel);
            }

            equalsLabel.setTextFill(Color.BLACK);
            equalsLabel.setText("  =");

            HBoxDiceValue.getChildren().add(equalsLabel);
        }

        System.out.println(total);
        if(total <= 1){
            diceValue.setTextFill(Color.RED);
        }
        else if(total >= selectedValueDiceType + numDiceBonus){
            diceValue.setTextFill(Color.GREEN);
        }
        HBoxDiceValue.getChildren().remove(diceValue);
        HBoxDiceValue.getChildren().add(diceValue);
        diceValue.setText(Integer.toString(total));
    }

}