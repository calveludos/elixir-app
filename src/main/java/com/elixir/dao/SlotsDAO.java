package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Slots;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SlotsDAO extends CrudDAO<Slots> {

    @Override
    public int create(Slots slots) throws SQLException {
        String query = "INSERT INTO Slots (I_level, II_level, III_level, IV_level, V_level, VI_level, VII_level, VIII_level, IX_level) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, slots.getI_level());
            stmt.setInt(2, slots.getII_level());
            stmt.setInt(3, slots.getIII_level());
            stmt.setInt(4, slots.getIV_level());
            stmt.setInt(5, slots.getV_level());
            stmt.setInt(6, slots.getVI_level());
            stmt.setInt(7, slots.getVII_level());
            stmt.setInt(8, slots.getVIII_level());
            stmt.setInt(9, slots.getIX_level());

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

            stmt.close();
        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
        return generatedId;
    }

    @Override
    public void update(Slots slots) throws SQLException {
        String query = "UPDATE Slots SET I_level = ?, II_level = ?, III_level = ?, IV_level = ?, " +
                "V_level = ?, VI_level = ?, VII_level = ?, VIII_level = ?, IX_level = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, slots.getI_level());
            stmt.setInt(2, slots.getII_level());
            stmt.setInt(3, slots.getIII_level());
            stmt.setInt(4, slots.getIV_level());
            stmt.setInt(5, slots.getV_level());
            stmt.setInt(6, slots.getVI_level());
            stmt.setInt(7, slots.getVII_level());
            stmt.setInt(8, slots.getVIII_level());
            stmt.setInt(9, slots.getIX_level());
            stmt.setInt(10, slots.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Slots> read() throws SQLException {
        String query = "SELECT * FROM Slots";
        ResultSet resultSet = null;
        Map<Integer, Slots> slotsMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Slots slots = new Slots();
                slots.setId(resultSet.getInt("id"));
                slots.setI_level(resultSet.getInt("I_level"));
                slots.setII_level(resultSet.getInt("II_level"));
                slots.setIII_level(resultSet.getInt("III_level"));
                slots.setIV_level(resultSet.getInt("IV_level"));
                slots.setV_level(resultSet.getInt("V_level"));
                slots.setVI_level(resultSet.getInt("VI_level"));
                slots.setVII_level(resultSet.getInt("VII_level"));
                slots.setVIII_level(resultSet.getInt("VIII_level"));
                slots.setIX_level(resultSet.getInt("IX_level"));

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
        String query = "DELETE FROM Slots WHERE id = ?";

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
