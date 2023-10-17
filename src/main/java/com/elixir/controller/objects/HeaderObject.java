package com.elixir.controller.objects;

import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.paint.*;

public class HeaderObject extends HBox {
    public HeaderObject() {
        // Defina as propriedades da HBox
        setAlignment(Pos.CENTER_LEFT);
        setPrefHeight(114.0);
        setPrefWidth(1000.0);
        setStyle("-fx-background-color: #434343;");
        setPadding(new Insets(10.0));

        // Logo e texto
        ImageView logo = new ImageView(new Image("/media/logoElixir.png"));
        logo.setFitHeight(95.0);
        logo.setFitWidth(84.0);
        setMargin(logo, new Insets(0, 0, 0, 10.0));

        VBox logoAndText = new VBox(5.0);
        logoAndText.setAlignment(Pos.CENTER_LEFT);

        Text headerText = new Text("ELIXIR MAKER");
        headerText.setFill(Color.WHITE);
        headerText.setFont(Font.font("System Bold", 20.0));
        headerText.setStyle("-fx-font-style: italic");

        Line headerLine = new Line(0, 0, 120.0, 0);
        headerLine.setStroke(Color.WHITE);
        headerLine.setStrokeWidth(3.0);

        logoAndText.getChildren().addAll(headerText, headerLine);
        setMargin(logoAndText, new Insets(0, 0, 28.0, 90.0));

        // Nome de usuário e foto de perfil
        HBox userBox = new HBox(10.0);
        userBox.setAlignment(Pos.TOP_RIGHT);

        Text userName = new Text("Nome de Usuário");
        userName.setFill(Color.WHITE);
        userName.setFont(Font.font("System Bold", 16.0));
        userName.setStyle("-fx-font-style: italic");

        ImageView profileImage = new ImageView(new Image("/media/emptyPerfilImage.png"));
        profileImage.setFitHeight(35.0);
        profileImage.setFitWidth(35.0);

        userBox.getChildren().addAll(userName, profileImage);
        setMargin(userBox, new Insets(0, 10.0, 0, 0));

        getChildren().addAll(logo, logoAndText, userBox);
    }
}
