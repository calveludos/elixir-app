package com.teamvectora.elixirapi.controller.objects;

import com.teamvectora.elixirapi.manager.PaneManager;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ValidationButton extends Button {
    public ValidationButton(String s) {
        super(s);
        setFont(Font.font("System Bold", 16.0));
        setStyle("-fx-background-color: #897a5f;");
        setTextFill(Color.WHITE);
        setOnAction(event -> {
            PaneManager manager = new PaneManager();
            manager.openPane("validationEmailPane");
        });
    }
}
