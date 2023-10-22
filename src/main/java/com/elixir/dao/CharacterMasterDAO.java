package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Character;

import java.sql.*;

public class CharacterMasterDAO {
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public int create(Character character) throws SQLException {
        String query = "INSERT INTO `Character` (id_alignment, id_attribute, id_class, id_race, id_folder, name, player_name, experience, height, weight, current_pv, max_pv, class_armor_bonus, apperance, background, image_path) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, character.getAlignmentId());
            stmt.setInt(2, character.getAttributeId());
            stmt.setInt(3, character.getClassId());
            stmt.setInt(4, character.getRaceId());
            stmt.setInt(5, character.getFolderId());
            stmt.setString(6, character.getName());
            stmt.setString(7, character.getPlayerName());
            stmt.setInt(8, character.getExperience());
            stmt.setInt(9, character.getHeight());
            stmt.setInt(10, character.getWeight());
            stmt.setInt(11, character.getCurrentPv());
            stmt.setInt(12, character.getMaxPv());
            stmt.setInt(13, character.getClassArmorBonus());
            stmt.setString(14, character.getAppearance());
            stmt.setString(15, character.getBackground());
            stmt.setString(16, character.getImagePath());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int primaryKey = generatedKeys.getInt(1);
                    generatedId = primaryKey;
                    System.out.println("Chave primária gerada: " + primaryKey);
                }
                generatedKeys.close();
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
        return generatedId;
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
