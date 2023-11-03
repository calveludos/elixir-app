package com.elixir.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CharacterViewDAOTest {
    @Test
    public void readeTest() throws SQLException {
        CharacterViewDAO viewDAO = new CharacterViewDAO();
        viewDAO.read(1);
    }

}