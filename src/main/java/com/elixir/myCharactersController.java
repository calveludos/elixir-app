package com.elixir;

import com.elixir.dao.CharacterDAO;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Map;

public class myCharactersController {

    @FXML
    private Button createCharacterMenuButton;

    @FXML
    private Button myCharacterMenuButton;

    @FXML
    private HBox hboxCharacters;

    @FXML
    private void initialize(){
        Map<Integer, Character> characters = null;

        try {
            CharacterDAO characterDAO = new CharacterDAO();
            characters = characterDAO.read();
        } catch (SQLException e){
            e.printStackTrace();
        }

        if (!characters.isEmpty()) {
            int sizeCharacters = characters.size();
            int current = 0;

            for (Character character : characters.values()) {
                current++;
                boolean isLast = sizeCharacters == current;
                hboxCharacters.getChildren().add(createCharacterPane(character, isLast));
            }
        } else {
            Label label = new Label("Nada aqui ainda");
            label.setPrefSize(595, 70);
            label.setTextFill(Color.valueOf("#110000"));
            label.setFont(new Font(18));
            label.setAlignment(javafx.geometry.Pos.BOTTOM_CENTER);

            hboxCharacters.getChildren().add(label);
        }

    }
    @FXML
    void characterPane(ActionEvent event){

    }

    public void createCharacterButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    void myCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager((Stage) myCharacterMenuButton.getScene().getWindow());
        paneManager.openPane("myCharactersPane");
    }

    private HBox createCharacterPane(Character character, boolean isLast) {
        ImageView imageView = new ImageView(new Image("/media/emptyImage.jpg"));
        imageView.setFitHeight(92);
        imageView.setFitWidth(114);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        Label nameCharacterLabel = new Label();
        nameCharacterLabel.setText(character.getName());
        setStyleName(nameCharacterLabel, isLast);

        Label raceCharacterLabel = new Label();
        raceCharacterLabel.setText(getRaceId(character.getRaceId()));
        setStyle(raceCharacterLabel);

        Label classCharacterLabel = new Label();
        classCharacterLabel.setText(getClassId(character.getClassId()));
        setStyle(classCharacterLabel);

        VBox vBox = new VBox(nameCharacterLabel, raceCharacterLabel, classCharacterLabel);
        vBox.setPrefHeight(200);
        vBox.setMinWidth(Label.USE_COMPUTED_SIZE);

        Pane pane = new Pane();
        pane.setPrefWidth(15);
        pane.setPrefHeight(Pane.USE_COMPUTED_SIZE);

        HBox hBox = new HBox(imageView, vBox, pane);
        hBox.setPrefHeight(92);
        hBox.setMinWidth(Label.USE_COMPUTED_SIZE);
        hBox.setId(String.valueOf(character.getId()));

        hBox.setOnMouseClicked(mouseEvent -> {

        });

        System.out.println("Label 'nameCharacterLabel' Width: " + nameCharacterLabel.getWidth());
        System.out.println("Label 'nameCharacterLabel' Height: " + nameCharacterLabel.getHeight());
        System.out.println("Label 'raceCharacterLabel' Width: " + raceCharacterLabel.getWidth());
        System.out.println("Label 'raceCharacterLabel' Height: " + raceCharacterLabel.getHeight());
        System.out.println("Label 'classCharacterLabel' Width: " + classCharacterLabel.getWidth());
        System.out.println("Label 'classCharacterLabel' Height: " + classCharacterLabel.getHeight());

        return hBox;
    }

    private void setStyleName(Label label, boolean isLast) {
        int size = label.getText().length();

        if(isLast){
            label.setPrefWidth(Label.USE_COMPUTED_SIZE);
        } else {
            double width = (size < 12)
                    ? (double) size * 10
                    : 120.0;
            label.setPrefWidth(width);
            System.out.println("Aqui: tamanho = " + label.getText().length());
            System.out.println("Aqui: size = " + width);
        }
        System.out.println("isLast = " + isLast);
        label.setPrefHeight(30);
        label.setFont(new Font(14));
        label.setTextFill(javafx.scene.paint.Color.valueOf("#110000"));
    }

    private void setStyle(Label label) {
        label.setMinWidth(Label.USE_COMPUTED_SIZE);
        label.setPrefHeight(30);
        label.setFont(new Font(14));
        label.setTextFill(javafx.scene.paint.Color.valueOf("#110000"));
    }

    private String getClassId(int classId) {
        switch (classId){
            case 1:
                return "Homem de Armas";
            case 2:
                return "Mago";
            case 3:
                return "Ladrão";
            case 4:
                return "Clérigo";
            default:
                return "";
        }
    }

    private String getRaceId(int raceId) {
        switch (raceId){
            case 1:
                return "Humano";
            case 2:
                return "Elfo";
            case 3:
                return "Anão";
            case 4:
                return "Halfing";
            default:
                return "";
        }
    }

    public void remPane() {
        // Verifica se há algum Pane na VBox characterContainer
        if (!hboxCharacters.getChildren().isEmpty()) {
            // Remove o último Pane adicionado (ou pode remover o que quiser, basta alterar o índice)
            hboxCharacters.getChildren().remove(hboxCharacters.getChildren().size() - 1);
        }
    }

}
