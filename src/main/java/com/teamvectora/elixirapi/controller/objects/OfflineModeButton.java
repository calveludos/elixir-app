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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OfflineModeButton extends ValidationButton{
    public OfflineModeButton(String s, String sqlUser, String sqlPassword) {
        super(s);
        setOnAction(event -> {
            ObjectSaveManager saveManager = new ObjectSaveManager();
            saveManager.saveObject("offline", true);

            ConnectionFactory.MYSQL_ADDON_USER = sqlUser;
            ConnectionFactory.MYSQL_ADDON_PASSWORD = sqlPassword;
            ConnectionFactory.MYSQL_ADDON_URL = "jdbc:mysql://localhost:3306/";

            try {
                ConnectionFactory.createDatabase();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }

            ConnectionFactory.MYSQL_ADDON_URL = "jdbc:mysql://localhost:3306/elixiroffline";

            User user = new User();
            user.setId(1);
            user.setUsername("OFF LINEMODE");
            user.setHashPassword("1234");
            user.setEmail("foo@email");
            user.setVerify(true);

            Folder folder = new Folder();
            folder.setId(1);
            folder.setName("default");
            folder.setUserId(1);

            UserDAO userDAO = new UserDAO();
            FolderDAO folderDAO = new FolderDAO();
            try {
                userDAO.create(user);
                folderDAO.create(folder);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Map<Integer, CharacterMaster> characterMasterMap = new HashMap<>();
            Map<Integer, Folder> folderMap = new HashMap<>();
            folderMap.put(folder.getId(), folder);

            saveManager.saveObject("user", user);
            saveManager.saveObject("folders", folderMap);
            saveManager.saveObject("characters", characterMasterMap);

            PaneManager manager = new PaneManager();
            manager.openPane("startScreenPane");
        });
    }
}
