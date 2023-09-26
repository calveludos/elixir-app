package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class InventoryDAO extends CrudDAO<Inventory> {

    @Override
    public int create(Inventory inventory) throws SQLException {
        String query = "INSERT INTO Inventory (id_character, id_item, type_item_id) VALUES (?, ?, ?)";
        int generatedId = -1;
        
        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, inventory.getId_character());
            stmt.setInt(2, inventory.getId_item());
            stmt.setInt(3, inventory.getType_item_id());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int primaryKey = generatedKeys.getInt(1);
                    generatedId = primaryKey;
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
    public void update(Inventory inventory) throws SQLException {
        String query = "UPDATE Inventory SET id_character = ?, id_item = ?, type_item_id = ? WHERE id = ?";
        
        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, inventory.getId_character());
            stmt.setInt(2, inventory.getId_item());
            stmt.setInt(3, inventory.getType_item_id());
            stmt.setInt(4, inventory.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Inventory> read() throws SQLException {
        String query = "SELECT * FROM Inventory";
        ResultSet resultSet = null;
        Map<Integer, Inventory> inventoryMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Inventory inventory = new Inventory();
                inventory.setId(resultSet.getInt("id"));
                inventory.setId_character(resultSet.getInt("id_character"));
                inventory.setId_item(resultSet.getInt("id_item"));
                inventory.setType_item_id(resultSet.getInt("type_item_id"));

                inventoryMap.put(inventory.getId(), inventory);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return inventoryMap;
    }

    @Override
    public void delete(Inventory inventory) throws SQLException {
        String query = "DELETE FROM Inventory WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, inventory.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
    }
}
