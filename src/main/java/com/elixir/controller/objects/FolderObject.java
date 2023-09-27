package com.elixir.controller.objects;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.scene.image.*;

public class FolderObject extends HBox {
    private final ImageView imageView;
    private final Text text;

    public FolderObject(String nomePasta) {
        imageView = new ImageView(new Image("/media/emptyImage.jpg"));
        imageView.setFitHeight(48.0);
        imageView.setFitWidth(52.0);

        text = new Text(nomePasta);
        text.setFill(Color.web("#4b5c6b"));
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);

        Font font = Font.font("System Bold", FontWeight.BOLD, 16.0);
        text.setFont(font);

        this.getChildren().addAll(imageView, text);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Text getText() {
        return text;
    }
}