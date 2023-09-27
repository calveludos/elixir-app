package com.elixir.controller.objects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.scene.image.*;

import static javafx.scene.layout.VBox.setVgrow;

public class CharacterObject extends HBox {

    public CharacterObject(String name, String race, String classe) {
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
        Text nomeText = createText(name);
        Text classeText = createText(classe);
        Text racaText = createText(race);

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