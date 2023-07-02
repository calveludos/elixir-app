package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterDAO implements DAO{
    @Override
    public void create(Model character) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnection();
            String query = "INSERT INTO `Character` (id_attribute, `race`, `name`, experience, heigth, weight, current_pv, max_pv) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, ((com.elixir.model.Character) character).getAttributeId());
            stmt.setString(2, ((com.elixir.model.Character)character).getRace());
            stmt.setString(3, ((com.elixir.model.Character) character).getName());
            stmt.setInt(4, ((com.elixir.model.Character) character).getExperience());
            stmt.setInt(5, ((com.elixir.model.Character) character).getHeight());
            stmt.setInt(6, ((com.elixir.model.Character) character).getWeight());
            stmt.setInt(7, ((com.elixir.model.Character) character).getCurrentPv());
            stmt.setInt(8, ((com.elixir.model.Character) character).getMaxPv());
            stmt.executeUpdate();

        } finally {
            // Fecha os recursos
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void update(Model model) throws SQLException {

    }

    @Override
    public void read(Model model) throws SQLException {

    }

    @Override
    public void delete(Model model) throws SQLException {

    }
}
