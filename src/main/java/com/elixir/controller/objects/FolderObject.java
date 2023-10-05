package com.elixir.controller.objects;

import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Folder;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.stage.Stage;

import static javafx.scene.layout.VBox.setVgrow;

public class FolderObject extends HBox {
    private final ImageView imageView;
    private final Text text;

    public FolderObject(Folder folder) {
        imageView = new ImageView(new Image("/media/pastImage.png"));
        imageView.setFitHeight(48.0);
        imageView.setFitWidth(52.0);

        text = new Text(folder.getName());
        text.setFill(Color.web("#4b5c6b"));
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);

        Font font = Font.font("System Bold", FontWeight.BOLD, 16.0);
        text.setFont(font);
        setHgrow(this, Priority.ALWAYS);
        setVgrow(this, Priority.ALWAYS);
        setSpacing(5.0);

        this.getChildren().addAll(imageView, text);

        this.setOnMouseClicked(mouseEvent -> {
            ObjectSaveManager saver = new ObjectSaveManager();
            saver.saveObject("folder", folder);
            saver.printMap();
            Folder folder1 = (Folder) saver.getObject("folder");
            System.out.println(folder1.getId());

            PaneManager paneManager = new PaneManager((Stage) this.getScene().getWindow());
            paneManager.openPane("folderCharacters");
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Text getText() {
        return text;
    }
}