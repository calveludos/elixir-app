package com.elixir.controller.objects;

import com.elixir.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.paint.*;

public class HeaderObject extends HBox {
    public HeaderObject() {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/objects/headerObject.fxml"));


        // Defina as propriedades da HBox
        setAlignment(Pos.TOP_LEFT);
        setPrefHeight(114.0);
        setPrefWidth(1000.0);
        setMaxHeight(116.2);
        setStyle("-fx-background-color: #434343;");
        setPadding(new Insets(10.0));

        // Logo e texto
        ImageView logo = new ImageView(new Image("/media/logoElixir.png"));
        logo.setFitHeight(95.0);
        logo.setFitWidth(84.0);
        setMargin(logo, new Insets(0, 0, 0, 10.0));

        VBox lineAndText = new VBox(5.0);
        lineAndText.setAlignment(Pos.CENTER_LEFT);
        setMargin(logo, new Insets(0, 0, 28.0, 0));

        Text headerText = new HeaderText("ELIXIR MAKER");
        headerText.setFill(Color.WHITE);
        headerText.setFont(Font.font("Cardinal", 20.0));

        Line headerLine = new Line(0, 0, 120.0, 0);
        headerLine.setStroke(Color.WHITE);
        headerLine.setStrokeWidth(3.0);

        lineAndText.getChildren().addAll(headerText, headerLine);

        Region region = new Region();
        region.setMaxHeight(0.0);
        setHgrow(region, Priority.ALWAYS);

        // Nome de usuário e foto de perfil

        Text userName = new HeaderText("Nome de Usuário");
        userName.setFill(Color.WHITE);
        userName.setFont(Font.font("System Bold Italic", 16.0));

        ImageView profileImage = new ImageView(new Image("/media/emptyPerfilImage.png"));
        profileImage.setFitHeight(35.0);
        profileImage.setFitWidth(35.0);
        setMargin(userName, new Insets(profileImage.getFitHeight()/2));

        getChildren().addAll(logo, lineAndText, region, userName, profileImage);
    }
}
