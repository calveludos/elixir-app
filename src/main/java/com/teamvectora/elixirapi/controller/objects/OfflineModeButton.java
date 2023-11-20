package com.teamvectora.elixirapi.controller.objects;

import com.teamvectora.elixirapi.dao.CharacterMasterDAO;
import com.teamvectora.elixirapi.dao.FolderDAO;
import com.teamvectora.elixirapi.dao.UserDAO;
import com.teamvectora.elixirapi.factory.ConnectionFactory;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Folder;
import com.teamvectora.elixirapi.model.User;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OfflineModeButton extends Button {
    public OfflineModeButton(String s) {
        super(s);
        setFont(Font.font("System Bold", 16.0));
        setStyle("-fx-background-color: #897a5f;");
        setTextFill(Color.WHITE);
        setOnAction(event -> {
            PaneManager manager = new PaneManager();
            manager.openPane("initialScreenPane");
        });
    }
}
