package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Character;
import com.elixir.model.CharacterMaster;

import java.sql.*;

public class CharacterMasterDAO {
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public int create(CharacterMaster characterMaster) throws SQLException {
        AttributeDAO attributeDAO = new AttributeDAO();
        characterMaster.getCharacter().setAttributeId(attributeDAO.create(characterMaster.getAttribute()));

        CharacterDAO characterDAO = new CharacterDAO();
        characterDAO.create(characterMaster.getCharacter());

        return characterMaster.getCharacter().getId();
    }


    protected void closeResources() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
