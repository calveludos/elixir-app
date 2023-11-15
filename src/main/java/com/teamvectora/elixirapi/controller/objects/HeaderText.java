package com.teamvectora.elixirapi.controller.objects;
import javafx.scene.text.*;
public class HeaderText extends Text {
    public HeaderText(String text) {
        setStyle(
                        "-fx-font-size: 50;" +
                        "-fx-font-family: 'Cardinal';"
        );
    }
}
