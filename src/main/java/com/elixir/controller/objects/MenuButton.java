package com.elixir.controller.objects;

import javafx.scene.control.Button;

public class MenuButton extends Button {
    public MenuButton(String text) {
        super(text);
        setPrefHeight(40.0);
        setPrefWidth(208.0);
        setStyle(
                "-fx-font-size: 13;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: #897A5F;" +
                        "-fx-background-radius: 3;" +
                        "-fx-border-radius: 3;" +
                        "-fx-effect: dropshadow(gaussian, #3C3B3B, 5, 0.5, 3, 3);" +
                        "-fx-transition: -fx-scale-x 0.3s cubic-bezier(0.68, -0.55, 0.27, 1.55), " +
                        "-fx-scale-y 0.3s cubic-bezier(0.68, -0.55, 0.27, 1.55);"
        );

        setOnMouseEntered(e -> {
            setStyle(
                    "-fx-font-size: 13;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-color: #75624F;" +
                            "-fx-cursor: hand;" +
                            "-fx-background-radius: 3;" +
                            "-fx-border-radius: 3;" +
                            "-fx-effect: dropshadow(gaussian, #3C3B3B, 5, 0.5, 3, 3);" +
                            "-fx-scale-x: 1.05;" +
                            "-fx-scale-y: 1.05;"
            );
        });

        setOnMouseExited(e -> {
            setStyle(
                    "-fx-font-size: 13;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-color: #897A5F;" +
                            "-fx-background-radius: 3;" +
                            "-fx-border-radius: 3;" +
                            "-fx-effect: dropshadow(gaussian, #3C3B3B, 5, 0.5, 3, 3);" +
                            "-fx-transition: -fx-scale-x 0.3s cubic-bezier(0.68, -0.55, 0.27, 1.55), " +
                            "-fx-scale-y 0.3s cubic-bezier(0.68, -0.55, 0.27, 1.55);"

            );
            });
        }
    }