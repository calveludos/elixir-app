package com.teamvectora.elixirapi.controller.objects;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SideMenuObject extends VBox {
    MenuController controller;

    public SideMenuObject() {
        controller = new MenuController();

        // Definir as propriedades da VBox
        setAlignment(Pos.TOP_CENTER);
        setPrefHeight(800.0);
        setPrefWidth(260.0);
        setSpacing(17.0);
        setStyle("-fx-background-color: #e7e4df;");

        // Ícone e texto do Menu Principal
        HBox menuHeader = new HBox(2.0);
        menuHeader.setAlignment(Pos.CENTER_LEFT);
        ImageView menuIcon = new ImageView(new Image("/media/menuIcon.png"));
        menuIcon.setFitHeight(30.0);
        menuIcon.setFitWidth(30.0);
        HBox.setMargin(menuIcon, new Insets(0, 0, 0, 15.0));

        Text menuText = new Text("MENU PRINCIPAL");
        menuText.setFill(Paint.valueOf("#4b5c6b"));
        menuText.setStrokeType(StrokeType.OUTSIDE);
        menuText.setStrokeWidth(0.0);
        menuText.setFont(Font.font(16.0));
        menuText.setStyle("-fx-font-weight: bold; -fx-font-style: italic;");
        HBox.setMargin(menuText, new Insets(4.0, 0, 0, 0));

        menuHeader.getChildren().addAll(menuIcon, menuText);

        // Botões
        VBox buttonContainer = new VBox(25.0);
        buttonContainer.setAlignment(Pos.CENTER);
        Button button1 = new MenuButton("TELA INICIAL");
        Button button2 = new MenuButton("MEUS PERSONAGENS");
        Button button3 = new MenuButton("CRIAR NOVA FICHA");
        Button button4 = new MenuButton("ROLAR DADOS");
        button1.setId("startPaneMenuButton");
        button2.setId("myCharactersMenuButton");
        button3.setId("createNewCharacterMenuButton");
        button4.setId("rollDiceButton");

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.startPaneMenuButtonAction(event);
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.myCharactersMenuButtonAction(event);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.createNewCharacterMenuButtonAction(event);
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.diceMenuButtonAction(event);
            }
        });

        buttonContainer.getChildren().addAll(button1, button2, button3, button4);
        buttonContainer.setPadding(new Insets(0, 0, 0, 20.0));

        // Adicionar todos os elementos à VBox
        getChildren().addAll(menuHeader, buttonContainer);
        setPadding(new Insets(120.0, 0, 0, 0));
    }
}
