package com.teamvectora.elixirapi.dao;

import com.teamvectora.elixirapi.factory.ConnectionFactory;
import com.teamvectora.elixirapi.model.Attribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AttributeDAO extends CrudDAO<Attribute> {

    @Override
    public int create(Attribute attribute) throws SQLException {
        String query = "INSERT INTO `Attribute` (strength, dexterity, constitution, intelligence, wisdom, charisma) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;
        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, attribute.getStrength());
            stmt.setInt(2, attribute.getDexterity());
            stmt.setInt(3, attribute.getConstitution());
            stmt.setInt(4, attribute.getIntelligence());
            stmt.setInt(5, attribute.getWisdom());
            stmt.setInt(6, attribute.getCharisma());

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
    public void update(Attribute attribute) throws SQLException {
        String query = "UPDATE `Attribute` SET strength = ?, dexterity = ?, constitution = ?, intelligence = ?, wisdom = ?, charisma = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, attribute.getStrength());
            stmt.setInt(2, attribute.getDexterity());
            stmt.setInt(3, attribute.getConstitution());
            stmt.setInt(4, attribute.getIntelligence());
            stmt.setInt(5, attribute.getWisdom());
            stmt.setInt(6, attribute.getCharisma());
            stmt.setInt(7, attribute.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Attribute> read() throws SQLException {
        String query = "SELECT * FROM `Attribute`";
        ResultSet resultSet = null;
        Map<Integer, Attribute> attributeMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Attribute attribute = new Attribute();
                attribute.setId(resultSet.getInt("id"));
                attribute.setStrength(resultSet.getInt("strength"));
                attribute.setDexterity(resultSet.getInt("dexterity"));
                attribute.setConstitution(resultSet.getInt("constitution"));
                attribute.setIntelligence(resultSet.getInt("intelligence"));
                attribute.setWisdom(resultSet.getInt("wisdom"));
                attribute.setCharisma(resultSet.getInt("charisma"));

                attributeMap.put(attribute.getId(), attribute);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return attributeMap;
    }

    public Map<Integer, Attribute> readQuery() throws SQLException {
        ResultSet resultSet = null;
        Map<Integer, Attribute> attributeMap = new HashMap<>();

        try {
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Attribute attribute = new Attribute();
                attribute.setId(resultSet.getInt("id"));
                attribute.setStrength(resultSet.getInt("strength"));
                attribute.setDexterity(resultSet.getInt("dexterity"));
                attribute.setConstitution(resultSet.getInt("constitution"));
                attribute.setIntelligence(resultSet.getInt("intelligence"));
                attribute.setWisdom(resultSet.getInt("wisdom"));
                attribute.setCharisma(resultSet.getInt("charisma"));

                attributeMap.put(attribute.getId(), attribute);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return attributeMap;
    }

    @Override
    public Map<Integer, Attribute> read(Attribute filter) throws SQLException {
        String query = "SELECT * FROM `Attribute` WHERE 1=1";

        if (filter.getId() != 0) {
            query += " AND id = " + filter.getId();
        }
        if (filter.getStrength() != 0) {
            query += " AND strength = " + filter.getStrength();
        }
        if (filter.getDexterity() != 0) {
            query += " AND dexterity = " + filter.getDexterity();
        }
        if (filter.getConstitution() != 0) {
            query += " AND constitution = " + filter.getConstitution();
        }
        if (filter.getIntelligence() != 0) {
            query += " AND intelligence = " + filter.getIntelligence();
        }
        if (filter.getWisdom() != 0) {
            query += " AND wisdom = " + filter.getWisdom();
        }
        if (filter.getCharisma() != 0) {
            query += " AND charisma = " + filter.getCharisma();
        }

        ResultSet resultSet = null;
        Map<Integer, Attribute> attributeMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Attribute attribute = new Attribute();
                attribute.setId(resultSet.getInt("id"));
                attribute.setStrength(resultSet.getInt("strength"));
                attribute.setDexterity(resultSet.getInt("dexterity"));
                attribute.setConstitution(resultSet.getInt("constitution"));
                attribute.setIntelligence(resultSet.getInt("intelligence"));
                attribute.setWisdom(resultSet.getInt("wisdom"));
                attribute.setCharisma(resultSet.getInt("charisma"));

                attributeMap.put(attribute.getId(), attribute);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return attributeMap;
    }

    @Override
    public void delete(Attribute attribute) throws SQLException {
        String query = "DELETE FROM `Attribute` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, attribute.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }
}
