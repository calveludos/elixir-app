package com.teamvectora.elixirapi;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MainAppTest {

    @Test
    void newCharacterTest() throws SQLException {
       /* ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.cleanObjects();
        String query = "SELECT c.* FROM `Character` c JOIN Folder f\n" +
                "ON c.id_folder = f.id \n" +
                "WHERE f.id_user = ?;";

        CharacterDAO characterDAO = new CharacterDAO();
        characterDAO.conn = ConnectionFactory.createConnection();
        characterDAO.stmt = characterDAO.conn.prepareStatement(query);
        characterDAO.stmt.setInt(1, 4);

        FolderDAO folderDAO = new FolderDAO();
        Folder folderFilter = new Folder();
        folderFilter.setUserId(4);

        Map<Integer, Folder> folderMap;
        Map<Integer, Character> characterMap;
        try {
            folderMap = folderDAO.read(folderFilter);
            characterMap = characterDAO.readQuery();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        saveManager.saveObject("folders", folderMap);
        saveManager.saveObject("characters", characterMap);
        MainApp.main(new String[]{"createCharacterDatePane"});*/
    }
}