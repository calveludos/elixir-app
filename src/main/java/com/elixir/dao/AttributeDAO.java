package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Attribute;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AttributeDAO extends CrudDAO<Attribute> {
    @Override
    public void create(Attribute attribute) throws SQLException {
        String query = "INSERT INTO Attribute (strength, dexterity, constitution, intelligence, wisdom, charisma) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, attribute.getStrength());
            stmt.setInt(2, attribute.getDexterity());
            stmt.setInt(3, attribute.getConstitution());
            stmt.setInt(4, attribute.getIntelligence());
            stmt.setInt(5, attribute.getWisdom());
            stmt.setInt(6, attribute.getCharisma());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public void update(Attribute attribute) throws SQLException {
        String query = "UPDATE Attribute SET strength = ?, dexterity = ?, constitution = ?, intelligence = ?, wisdom = ?, charisma = ? WHERE id = ?";

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
        String query = "SELECT * FROM Attribute";
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
        String query = "DELETE FROM Attribute WHERE id = ?";

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