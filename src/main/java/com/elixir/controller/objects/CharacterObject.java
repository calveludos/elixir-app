package com.elixir.controller.objects;
import com.elixir.controller.MyCharactersController;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.scene.control.Alert.AlertType;

import java.util.Map;

import static javafx.scene.layout.VBox.setVgrow;

public class CharacterObject extends HBox {

    public CharacterObject(Character character) {
        // Crie um ImageView com a imagem desejada
        ImageView imageView = new ImageView(new Image("/media/emptyImage.png"));
        imageView.setFitHeight(108.0);
        imageView.setFitWidth(118.0);

        // Crie um VBox para os textos
        VBox textVBox = new VBox(5);
        textVBox.setPrefHeight(200.0);
        textVBox.setPrefWidth(100.0);
        textVBox.setSpacing(5.0);

        // Adicione os textos
        Text nomeText = createText(character.getName());
        Text classeText = createText(MyCharactersController.getClassId(character.getClassId()));
        Text racaText = createText(MyCharactersController.getRaceId(character.getRaceId()));

        // Adicione os textos ao VBox
        textVBox.getChildren().addAll(nomeText, classeText, racaText);

        VBox.setMargin(this, new Insets(10, 0, 0, 0));

        // Adicione o ImageView e o VBox ao HBox
        getChildren().addAll(imageView, textVBox);

        // Configure o espaçamento, alinhamento e crescimento do HBox
        setSpacing(5.0);
        setAlignment(Pos.TOP_LEFT);
        setHgrow(this, Priority.ALWAYS);
        setVgrow(this, Priority.ALWAYS);

        // Configure o preenchimento (padding) do VBox
        VBox.setMargin(textVBox, new Insets(20.0, 0, 20.0, 0));

        setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                System.out.println("clicou");
                ObjectSaveManager saver = new ObjectSaveManager();
                Map<Integer, Attribute> attributeMap = (Map<Integer, Attribute>) saver.getObject("attributes");
                Attribute attribute = attributeMap.get(character.getAttributeId());
                System.out.println(attribute);
                saver.saveObject("character", character);
                saver.saveObject("attribute", attribute);

                PaneManager manager = new PaneManager();
                manager.openPane("viewCharacterPage1");
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY){

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("DELETAR PERSONAGEM");
                alert.setContentText("Você tem certeza que deseja deletar o personagem: " + character.getName() + "\n" +
                        "Essa ação não pode ser desfeita");

                alert.showAndWait().ifPresent(response -> {
                    if (response.getText().equals("OK")){
                        alert.setHeaderText("Nao foi possível deletar por que não");
                        alert.setContentText("Nao");
                        alert.showAndWait();
                    }
                });
            }
        });
    }

    private Text createText(String text) {
        Text labelText = new Text(text);
        labelText.setFill(Color.web("#4b5c6b"));
        labelText.setStrokeType(StrokeType.OUTSIDE);
        labelText.setStrokeWidth(0.0);
        labelText.setFont(Font.font("System Bold", FontWeight.BOLD, 16.0));
        return labelText;
    }
}