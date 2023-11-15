package com.teamvectora.elixirapi.dao;

import com.teamvectora.elixirapi.factory.ConnectionFactory;
import com.teamvectora.elixirapi.model.Slots;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SlotsDAO extends CrudDAO<Slots> {

    @Override
    public int create(Slots slots) throws SQLException {
        String query = "INSERT INTO `Slots` (id_character, I_level, II_level, III_level, IV_level, V_level, VI_level, VII_level, VIII_level, IX_level) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, slots.getCharacterId());
            stmt.setInt(2, slots.getILevel());
            stmt.setInt(3, slots.getIiLevel());
            stmt.setInt(4, slots.getIiiLevel());
            stmt.setInt(5, slots.getIvLevel());
            stmt.setInt(6, slots.getVLevel());
            stmt.setInt(7, slots.getViLevel());
            stmt.setInt(8, slots.getViiLevel());
            stmt.setInt(9, slots.getViiiLevel());
            stmt.setInt(10, slots.getIxLevel());

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
    public void update(Slots slots) throws SQLException {
        String query = "UPDATE `Slots` SET id_character = ?, I_level = ?, II_level = ?, III_level = ?, IV_level = ?, V_level = ?, VI_level = ?, VII_level = ?, VIII_level = ?, IX_level = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, slots.getCharacterId());
            stmt.setInt(2, slots.getILevel());
            stmt.setInt(3, slots.getIiLevel());
            stmt.setInt(4, slots.getIiiLevel());
            stmt.setInt(5, slots.getIvLevel());
            stmt.setInt(6, slots.getVLevel());
            stmt.setInt(7, slots.getViLevel());
            stmt.setInt(8, slots.getViiLevel());
            stmt.setInt(9, slots.getViiiLevel());
            stmt.setInt(10, slots.getIxLevel());
            stmt.setInt(11, slots.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Slots> read() throws SQLException {
        String query = "SELECT * FROM `Slots`";
        ResultSet resultSet = null;
        Map<Integer, Slots> slotsMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Slots slots = new Slots();
                slots.setId(resultSet.getInt("id"));
                slots.setCharacterId(resultSet.getInt("id_character"));
                slots.setILevel(resultSet.getInt("I_level"));
                slots.setIiLevel(resultSet.getInt("II_level"));
                slots.setIiiLevel(resultSet.getInt("III_level"));
                slots.setIvLevel(resultSet.getInt("IV_level"));
                slots.setVLevel(resultSet.getInt("V_level"));
                slots.setViLevel(resultSet.getInt("VI_level"));
                slots.setViiLevel(resultSet.getInt("VII_level"));
                slots.setViiiLevel(resultSet.getInt("VIII_level"));
                slots.setIxLevel(resultSet.getInt("IX_level"));

                slotsMap.put(slots.getId(), slots);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return slotsMap;
    }

    @Override
    public Map<Integer, Slots> read(Slots filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Slots` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getCharacterId() != 0) {
            query.append(" AND id_character = ").append(filter.getCharacterId());
        }
        if (filter.getILevel() != 0) {
            query.append(" AND I_level = ").append(filter.getILevel());
        }
        if (filter.getIiLevel() != 0) {
            query.append(" AND II_level = ").append(filter.getIiLevel());
        }
        if (filter.getIiiLevel() != 0) {
            query.append(" AND III_level = ").append(filter.getIiiLevel());
        }
        if (filter.getIvLevel() != 0) {
            query.append(" AND IV_level = ").append(filter.getIvLevel());
        }
        if (filter.getVLevel() != 0) {
            query.append(" AND V_level = ").append(filter.getVLevel());
        }
        if (filter.getViLevel() != 0) {
            query.append(" AND VI_level = ").append(filter.getViLevel());
        }
        if (filter.getViiLevel() != 0) {
            query.append(" AND VII_level = ").append(filter.getViiLevel());
        }
        if (filter.getViiiLevel() != 0) {
            query.append(" AND VIII_level = ").append(filter.getViiiLevel());
        }
        if (filter.getIxLevel() != 0) {
            query.append(" AND IX_level = ").append(filter.getIxLevel());
        }

        ResultSet resultSet = null;
        Map<Integer, Slots> slotsMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Slots slots = new Slots();
                slots.setId(resultSet.getInt("id"));
                slots.setCharacterId(resultSet.getInt("id_character"));
                slots.setILevel(resultSet.getInt("I_level"));
                slots.setIiLevel(resultSet.getInt("II_level"));
                slots.setIiiLevel(resultSet.getInt("III_level"));
                slots.setIvLevel(resultSet.getInt("IV_level"));
                slots.setVLevel(resultSet.getInt("V_level"));
                slots.setViLevel(resultSet.getInt("VI_level"));
                slots.setViiLevel(resultSet.getInt("VII_level"));
                slots.setViiiLevel(resultSet.getInt("VIII_level"));
                slots.setIxLevel(resultSet.getInt("IX_level"));

                slotsMap.put(slots.getId(), slots);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return slotsMap;
    }

    @Override
    public void delete(Slots slots) throws SQLException {
        String query = "DELETE FROM `Slots` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, slots.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }
}
