package com.teamvectora.elixirapi.dao;

import com.teamvectora.elixirapi.factory.ConnectionFactory;
import com.teamvectora.elixirapi.model.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class InventoryDAO extends CrudDAO<Inventory> {

    @Override
    public int create(Inventory inventory) throws SQLException {
        String query = "INSERT INTO `Inventory` (id_character, item_id, type_item_id) " +
                "VALUES (?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, inventory.getCharacterId());
            stmt.setInt(2, inventory.getItemId());
            stmt.setInt(3, inventory.getTypeItemId());

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
    public void update(Inventory inventory) throws SQLException {
        String query = "UPDATE `Inventory` SET character_id = ?, item_id = ?, type_item_id = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, inventory.getCharacterId());
            stmt.setInt(2, inventory.getItemId());
            stmt.setInt(3, inventory.getTypeItemId());
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
        String query = "SELECT * FROM `Inventory`";
        ResultSet resultSet = null;
        Map<Integer, Inventory> inventoryMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Inventory inventory = new Inventory();
                inventory.setId(resultSet.getInt("id"));
                inventory.setCharacterId(resultSet.getInt("character_id"));
                inventory.setItemId(resultSet.getInt("item_id"));
                inventory.setTypeItemId(resultSet.getInt("type_item_id"));

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
    public Map<Integer, Inventory> read(Inventory filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Inventory` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getCharacterId() != 0) {
            query.append(" AND id_character = ").append(filter.getCharacterId());
        }
        if (filter.getItemId() != 0) {
            query.append(" AND item_id = ").append(filter.getItemId());
        }
        if (filter.getTypeItemId() != 0) {
            query.append(" AND type_item_id = ").append(filter.getTypeItemId());
        }

        ResultSet resultSet = null;
        Map<Integer, Inventory> inventoryMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Inventory inventory = new Inventory();
                inventory.setId(resultSet.getInt("id"));
                inventory.setCharacterId(resultSet.getInt("id_character"));
                inventory.setItemId(resultSet.getInt("item_id"));
                inventory.setTypeItemId(resultSet.getInt("type_item_id"));

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
        String query = "DELETE FROM `Inventory` WHERE id = ?";

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
