package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Folder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class FolderDAO extends CrudDAO<Folder> {

    @Override
    public int create(Folder folder) throws SQLException {
        String query = "INSERT INTO `Folder` (id_user, name) VALUES (?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, folder.getUserId());
            stmt.setString(2, folder.getName());

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
    public void update(Folder folder) throws SQLException {
        String query = "UPDATE `Folder` SET id_user = ?, name = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, folder.getUserId());
            stmt.setString(2, folder.getName());
            stmt.setInt(3, folder.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Folder> read() throws SQLException {
        String query = "SELECT * FROM `Folder`";
        ResultSet resultSet = null;
        Map<Integer, Folder> folderMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Folder folder = new Folder();
                folder.setId(resultSet.getInt("id"));
                folder.setUserId(resultSet.getInt("id_user"));
                folder.setName(resultSet.getString("name"));

                folderMap.put(folder.getId(), folder);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return folderMap;
    }

    @Override
    public Map<Integer, Folder> read(Folder filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Folder` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getUserId() != 0) {
            query.append(" AND id_user = ").append(filter.getUserId());
        }
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            query.append(" AND name = '").append(filter.getName()).append("'");
        }

        ResultSet resultSet = null;
        Map<Integer, Folder> folderMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Folder folder = new Folder();
                folder.setId(resultSet.getInt("id"));
                folder.setUserId(resultSet.getInt("id_user"));
                folder.setName(resultSet.getString("name"));

                folderMap.put(folder.getId(), folder);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return folderMap;
    }

    @Override
    public void delete(Folder folder) throws SQLException {
        String query = "DELETE FROM `Folder` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, folder.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
    }
}
