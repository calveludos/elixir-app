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
        String query = "INSERT INTO `Character` (id_attribute, id_race, id_alignment, id_class, name, player_name, experience, height, weight, current_pv, max_pv, id_currency, slots, appearance, class_armor_bonus, background) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, character.getAttributeId());
            stmt.setInt(2, character.getRaceId());
            stmt.setInt(3, character.getAlignmentId());
            stmt.setInt(4, character.getClassId());
            stmt.setString(5, character.getName());
            stmt.setString(6, character.getPlayerName()); // Novo campo adicionado
            stmt.setInt(7, character.getExperience());
            stmt.setInt(8, character.getHeight());
            stmt.setInt(9, character.getWeight());
            stmt.setInt(10, character.getCurrentPv());
            stmt.setInt(11, character.getMaxPv());
            stmt.setInt(12, character.getCurrencyId());
            stmt.setInt(13, character.getSlots());
            stmt.setString(14, character.getAppearance());
            stmt.setInt(15, character.getClassArmorBonus());
            stmt.setString(16, character.getBackground()); // Novo campo adicionado
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                character.setId(generatedId);
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
        String query = "UPDATE `Character` SET id_attribute = ?, id_race = ?, id_alignment = ?, id_class = ?, name = ?, player_name = ?, experience = ?, height = ?, weight = ?, current_pv = ?, max_pv = ?, id_currency = ?, slots = ?, appearance = ?, class_armor_bonus = ?, background = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, character.getAttributeId());
            stmt.setInt(2, character.getRaceId());
            stmt.setInt(3, character.getAlignmentId());
            stmt.setInt(4, character.getClassId());
            stmt.setString(5, character.getName());
            stmt.setString(6, character.getPlayerName()); // Novo campo adicionado
            stmt.setInt(7, character.getExperience());
            stmt.setInt(8, character.getHeight());
            stmt.setInt(9, character.getWeight());
            stmt.setInt(10, character.getCurrentPv());
            stmt.setInt(11, character.getMaxPv());
            stmt.setInt(12, character.getCurrencyId());
            stmt.setInt(13, character.getSlots());
            stmt.setString(14, character.getAppearance());
            stmt.setInt(15, character.getClassArmorBonus());
            stmt.setString(16, character.getBackground()); // Novo campo adicionado
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
                character.setAttributeId(resultSet.getInt("id_attribute"));
                character.setRaceId(resultSet.getInt("id_race"));
                character.setAlignmentId(resultSet.getInt("id_alignment"));
                character.setClassId(resultSet.getInt("id_class"));
                character.setName(resultSet.getString("name"));
                character.setPlayerName(resultSet.getString("player_name")); // Novo campo adicionado
                character.setExperience(resultSet.getInt("experience"));
                character.setHeight(resultSet.getInt("height"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));
                character.setCurrencyId(resultSet.getInt("id_currency"));
                character.setSlots(resultSet.getInt("slots"));
                character.setAppearance(resultSet.getString("appearance"));
                character.setClassArmorBonus(resultSet.getInt("class_armor_bonus"));
                character.setBackground(resultSet.getString("background")); // Novo campo adicionado

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
