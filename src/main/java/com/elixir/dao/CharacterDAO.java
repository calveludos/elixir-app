package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Character;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CharacterDAO extends CrudDAO<Character> {

    @Override
    public int create(Character character) throws SQLException {
        String query = "INSERT INTO `Character` (id_attribute, id_race, id_alignment, id_class, name, player_name, id_folder, experience, level, height, weight, current_pv, max_pv, id_currency, id_slots, apperance, class_armor_bonus, background, image_path) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, character.getAttributeId());
            stmt.setInt(2, character.getRaceId());
            stmt.setInt(3, character.getAlignmentId());
            stmt.setInt(4, character.getClassId());
            stmt.setString(5, character.getName());
            stmt.setString(6, character.getPlayerName());
            stmt.setInt(7, character.getIdFolder());
            stmt.setInt(8, character.getExperience());
            stmt.setInt(9, character.getLevel());
            stmt.setInt(10, character.getHeight());
            stmt.setInt(11, character.getWeight());
            stmt.setInt(12, character.getCurrentPv());
            stmt.setInt(13, character.getMaxPv());
            stmt.setInt(14, character.getCurrencyId());
            stmt.setInt(15, character.getSlots());
            stmt.setString(16, character.getAppearance());
            stmt.setInt(17, character.getClassArmorBonus());
            stmt.setString(18, character.getBackground());
            stmt.setString(19, character.getImage());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    character.setId(generatedId);
                }
                generatedKeys.close();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
        return character.getId();
    }

    @Override
    public void update(Character character) throws SQLException {
        String query = "UPDATE `Character` SET id_attribute = ?, id_race = ?, id_alignment = ?, id_class = ?, name = ?, player_name = ?, id_folder = ?, experience = ?, level = ?, height = ?, weight = ?, current_pv = ?, max_pv = ?, id_currency = ?, id_slots = ?, apperance = ?, class_armor_bonus = ?, background = ?, image_path = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, character.getAttributeId());
            stmt.setInt(2, character.getRaceId());
            stmt.setInt(3, character.getAlignmentId());
            stmt.setInt(4, character.getClassId());
            stmt.setString(5, character.getName());
            stmt.setString(6, character.getPlayerName());
            stmt.setInt(7, character.getIdFolder());
            stmt.setInt(8, character.getExperience());
            stmt.setInt(9, character.getLevel());
            stmt.setInt(10, character.getHeight());
            stmt.setInt(11, character.getWeight());
            stmt.setInt(12, character.getCurrentPv());
            stmt.setInt(13, character.getMaxPv());
            stmt.setInt(14, character.getCurrencyId());
            stmt.setInt(15, character.getSlots());
            stmt.setString(16, character.getAppearance());
            stmt.setInt(17, character.getClassArmorBonus());
            stmt.setString(18, character.getBackground());
            stmt.setString(19, character.getImage());
            stmt.setInt(20, character.getId());

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
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name"));
                character.setIdFolder(resultSet.getInt("id_folder"));
                character.setExperience(resultSet.getInt("experience"));
                character.setLevel(resultSet.getInt("level"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setCurrencyId(resultSet.getInt("id_currency"));
                character.setSlots(resultSet.getInt("id_slots"));
                character.setAppearance(resultSet.getString("apperance"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setBackground(resultSet.getString("background"));
                character.setImage(resultSet.getString("image_path"));

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

    public Map<Integer, Character> read(Character filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Character` WHERE id");

        if (filter.getId() != 0) {
            query.append(" = ").append(filter.getId());
        }
        if (filter.getAttributeId() != 0) {
            query.append(" AND id_attribute = ").append(filter.getAttributeId());
        }
        if (filter.getRaceId() != 0) {
            query.append(" AND id_race = ").append(filter.getRaceId());
        }
        if (filter.getAlignmentId() != 0) {
            query.append(" AND id_alignment = ").append(filter.getAlignmentId());
        }
        if (filter.getClassId() != 0) {
            query.append(" AND id_class = ").append(filter.getClassId());
        }
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            query.append(" AND name = '").append(filter.getName()).append("'");
        }
        if (filter.getPlayerName() != null && !filter.getPlayerName().isEmpty()) {
            query.append(" AND player_name = '").append(filter.getPlayerName()).append("'");
        }
        if (filter.getIdFolder() != 0) {
            query.append(" AND id_folder = ").append(filter.getIdFolder());
        }
        if (filter.getLevel() != 0) {
            query.append(" AND level = ").append(filter.getLevel());
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
        if (filter.getCurrencyId() != 0) {
            query.append(" AND id_currency = ").append(filter.getCurrencyId());
        }
        if (filter.getSlots() != 0) {
            query.append(" AND id_slots = ").append(filter.getSlots());
        }
        if (filter.getAppearance() != null && !filter.getAppearance().isEmpty()) {
            query.append(" AND apperance = '").append(filter.getAppearance()).append("'");
        }
        if (filter.getClassArmorBonus() != 0) {
            query.append(" AND class_armor_bonus = ").append(filter.getClassArmorBonus());
        }
        if (filter.getBackground() != null && !filter.getBackground().isEmpty()) {
            query.append(" AND background = '").append(filter.getBackground()).append("'");
        }
        if (filter.getImage() != null && !filter.getImage().isEmpty()) {
            query.append(" AND image_path = '").append(filter.getImage()).append("'");
        }

        query.append(";");

        String queryString = query.toString();

        ResultSet resultSet = null;
        Map<Integer, Character> characterMap = new HashMap<>();
        System.out.println(queryString);

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(queryString);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getInt("id"));
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name"));
                character.setIdFolder(resultSet.getInt("id_folder"));
                character.setExperience(resultSet.getInt("experience"));
                character.setLevel(resultSet.getInt("level"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setCurrencyId(resultSet.getInt("id_currency"));
                character.setSlots(resultSet.getInt("id_slots"));
                character.setAppearance(resultSet.getString("apperance"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setBackground(resultSet.getString("background"));
                character.setImage(resultSet.getString("image_path"));

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
