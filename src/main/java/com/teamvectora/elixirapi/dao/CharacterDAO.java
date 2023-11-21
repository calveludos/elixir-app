package com.teamvectora.elixirapi.dao;

import com.teamvectora.elixirapi.factory.ConnectionFactory;
import com.teamvectora.elixirapi.model.Character;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CharacterDAO extends CrudDAO<Character> {

    @Override
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

    @Override
    public void update(Character character) throws SQLException {
        String query = "UPDATE `Character` SET id_alignment = ?, id_attribute = ?, id_class = ?, id_race = ?, id_folder = ?, name = ?, player_name = ?, experience = ?, height = ?, weight = ?, current_pv = ?, max_pv = ?, class_armor_bonus = ?, apperance = ?, background = ?, image_path = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

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
            stmt.setInt(17, character.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Character> read() throws SQLException {
        String query = "SELECT * FROM `Character`";
        ResultSet resultSet = null;
        Map<Integer, Character> characterMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getInt("id"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setFolderId(resultSet.getInt("id_folder"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name"));
                character.setExperience(resultSet.getInt("experience"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setAppearance(resultSet.getString("apperance"));
                character.setBackground(resultSet.getString("background"));
                character.setImagePath(resultSet.getString("image_path"));

                characterMap.put(character.getId(), character);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return characterMap;
    }

    @Override
    public Map<Integer, Character> read(Character filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Character` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getAlignmentId() != 0) {
            query.append(" AND id_alignment = ").append(filter.getAlignmentId());
        }
        if (filter.getAttributeId() != 0) {
            query.append(" AND id_attribute = ").append(filter.getAttributeId());
        }
        if (filter.getClassId() != 0) {
            query.append(" AND id_class = ").append(filter.getClassId());
        }
        if (filter.getRaceId() != 0) {
            query.append(" AND id_race = ").append(filter.getRaceId());
        }
        if (filter.getFolderId() != 0) {
            query.append(" AND id_folder = ").append(filter.getFolderId());
        }
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            query.append(" AND name = '").append(filter.getName()).append("'");
        }
        if (filter.getPlayerName() != null && !filter.getPlayerName().isEmpty()) {
            query.append(" AND player_name = '").append(filter.getPlayerName()).append("'");
        }
        if (filter.getExperience() != 0) {
            query.append(" AND experience = ").append(filter.getExperience());
        }
        if (filter.getHeight() != 0) {
            query.append(" AND height = ").append(filter.getHeight());
        }
        if (filter.getWeight() != 0) {
            query.append(" AND weight = ").append(filter.getWeight());
        }
        if (filter.getCurrentPv() != 0) {
            query.append(" AND current_pv = ").append(filter.getCurrentPv());
        }
        if (filter.getMaxPv() != 0) {
            query.append(" AND max_pv = ").append(filter.getMaxPv());
        }
        if (filter.getClassArmorBonus() != 0) {
            query.append(" AND class_armor_bonus = ").append(filter.getClassArmorBonus());
        }
        if (filter.getAppearance() != null && !filter.getAppearance().isEmpty()) {
            query.append(" AND apperance = '").append(filter.getAppearance()).append("'");
        }
        if (filter.getBackground() != null && !filter.getBackground().isEmpty()) {
            query.append(" AND background = '").append(filter.getBackground()).append("'");
        }
        if (filter.getImagePath() != null && !filter.getImagePath().isEmpty()) {
            query.append(" AND image_path = '").append(filter.getImagePath()).append("'");
        }

        ResultSet resultSet = null;
        Map<Integer, Character> characterMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getInt("id"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setFolderId(resultSet.getInt("id_folder"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name"));
                character.setExperience(resultSet.getInt("experience"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setAppearance(resultSet.getString("apperance"));
                character.setBackground(resultSet.getString("background"));
                character.setImagePath(resultSet.getString("image_path"));

                characterMap.put(character.getId(), character);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return characterMap;
    }

    public Map<Integer, Character> readQuery() throws SQLException {
        ResultSet resultSet = null;
        Map<Integer, Character> characterMap = new HashMap<>();

        try {
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getInt("id"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setFolderId(resultSet.getInt("id_folder"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name"));
                character.setExperience(resultSet.getInt("experience"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setAppearance(resultSet.getString("apperance"));
                character.setBackground(resultSet.getString("background"));
                character.setImagePath(resultSet.getString("image_path"));

                characterMap.put(character.getId(), character);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
           /* closeResources();
            if (resultSet != null) {
                resultSet.close();
            }*/
        }

        return characterMap;
    }

    @Override
    public void delete(Character character) throws SQLException {
        String query = "DELETE FROM `Character` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, character.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }
}
