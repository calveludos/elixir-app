package com.elixir.dao;

import com.elixir.model.*;
import com.elixir.model.Character;
import com.elixir.model.Currency;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterViewDAOTest {
    @Test
    public void readeTest() throws SQLException {
        CharacterViewDAO viewDAO = new CharacterViewDAO();
        Folder folder = new Folder();
        folder.setUserId(2);
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
        });
    }

}