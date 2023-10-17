package com.elixir.controller.objects;

import javafx.scene.control.Button;

public class MenuButton extends Button {
    public MenuButton(String text) {
        super(text);
        setPrefHeight(40.0);
        setPrefWidth(208.0);
        setStyle("-fx-background-color: #897a5f; -fx-text-fill: white;");
    }
}