package com.teamvectora.elixirapi.controller.objects;

import com.teamvectora.elixirapi.dao.CharacterDAO;
import com.teamvectora.elixirapi.dao.FolderDAO;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.Character;
import com.teamvectora.elixirapi.model.Folder;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.*;
import javafx.scene.image.*;

import java.sql.SQLException;
import java.util.Map;

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
            if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                ObjectSaveManager saver = new ObjectSaveManager();
                saver.saveObject("folder", folder);
                saver.printMap();
                Folder folder1 = (Folder) saver.getObject("folder");
                System.out.println(folder1.getId());

                PaneManager paneManager = new PaneManager();
                paneManager.openPane("folderCharacters");
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("DELETAR PASTAS");
                alert.setHeaderText("DELETAR A PASTA?");
                alert.setContentText("Você tem certeza que deseja deletar a pasta: " + folder.getName() + "\n" +
                        "Isso não irá deletar os persoangens dentro da pasta.");

                alert.showAndWait().ifPresent(response -> {
                    if (response.getText().equals("OK")){
                        ObjectSaveManager reader = new ObjectSaveManager();
                        CharacterDAO dao = new CharacterDAO();
                        FolderDAO folderDAO = new FolderDAO();

                        Map<Integer, Folder> folderMap = (Map<Integer, Folder>) reader.getObject("folders");
                        Map<Integer, Character> characterMap = (Map<Integer, Character>) reader.getObject("characters");

                        int defaultId = folderMap.values()
                                .stream()
                                .filter(f -> f.getName().equals("default"))
                                .map(Folder::getId)
                                .findFirst()
                                .orElse(-1);

                        Alert alert1 = new Alert(AlertType.ERROR);

                        for (Character character :
                                characterMap.values()) {
                            if (character.getFolderId() == folder.getId()){
                                character.setFolderId(defaultId);

                                try {
                                    dao.update(character);
                                } catch (SQLException e) {
                                    alert1.setTitle("ERRO");
                                    alert1.setHeaderText("Ocorreu um erro ao deletar essa pasta");
                                    alert1.showAndWait();

                                    throw new RuntimeException(e.getMessage());
                                }

                                characterMap.put(character.getId(), character);
                            }
                        }

                        try {
                            folderDAO.delete(folder);
                            folderMap.remove(folder.getId());
                        } catch (SQLException e) {
                            alert1.setTitle("ERRO");
                            alert1.setHeaderText("Ocorreu um erro ao deletar essa pasta");
                            alert1.showAndWait();

                            throw new RuntimeException(e.getMessage());
                        }

                        reader.saveObject("characters", characterMap);
                        reader.saveObject("folders", folderMap);

                        PaneManager paneManager = new PaneManager();
                        paneManager.openPane("myCharactersPane");
                    }
                });
            }
        });
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Text getText() {
        return text;
    }
}