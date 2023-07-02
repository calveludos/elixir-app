package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Character;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CharacterDAO extends CrudDAO<Character>{
    @Override
    public void create(Character character) throws SQLException {
        String query = "INSERT INTO `Character` (id_attribute, `id_race`, `name`, experience, heigth, weight, current_pv, max_pv) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, character.getAttributeId());
            stmt.setInt(2, character.getRaceId());
            stmt.setString(3, character.getName());
            stmt.setInt(4, character.getExperience());
            stmt.setInt(5, character.getHeight());
            stmt.setInt(6, character.getWeight());
            stmt.setInt(7, character.getCurrentPv());
            stmt.setInt(8, character.getMaxPv());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public void update(Character character) throws SQLException {
        String query = "UPDATE `Character` SET id_attribute = ?, id_race = ?, `name` = ?, experience = ?, heigth = ?, weight = ?, current_pv = ?, max_pv = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, character.getAttributeId());
            stmt.setInt(2, character.getRaceId());
            stmt.setString(3, character.getName());
            stmt.setInt(4, character.getExperience());
            stmt.setInt(5, character.getHeight());
            stmt.setInt(6, character.getWeight());
            stmt.setInt(7, character.getCurrentPv());
            stmt.setInt(8, character.getMaxPv());
            stmt.setInt(9, character.getId());

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
                character.setName(resultSet.getString("name"));
                character.setExperience(resultSet.getInt("experience"));
                character.setHeight(resultSet.getInt("heigth"));
                character.setWeight(resultSet.getInt("weight"));
                character.setCurrentPv(resultSet.getInt("current_pv"));
                character.setMaxPv(resultSet.getInt("max_pv"));

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
