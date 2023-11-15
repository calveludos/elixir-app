package com.teamvectora.elixirapi.dao;

import com.teamvectora.elixirapi.model.*;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Folder;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

class CharacterViewDAOTest {
    @Test
    public void readeTest() throws SQLException {
        CharacterMasterDAO viewDAO = new CharacterMasterDAO();
        Folder folder = new Folder();
        folder.setUserId(6);
        CharacterMaster master = new CharacterMaster();
        master.setFolder(folder);
        Map<Integer, CharacterMaster> characterMasterMap = viewDAO.read(master);
        characterMasterMap.values().forEach(characterMaster -> {
            System.out.println(characterMaster);
            System.out.println(characterMaster.getFolder());
            System.out.println(characterMaster.getAttribute());
            System.out.println(characterMaster.getCurrency());
            System.out.println(characterMaster.getSlots());
            System.out.println(characterMaster.getInventory());
            System.out.println(characterMaster.getSpeech());
            System.out.println(characterMaster.getSpells());
        });
    }

}