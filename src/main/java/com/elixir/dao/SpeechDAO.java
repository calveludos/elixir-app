package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Speech;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SpeechDAO extends CrudDAO<Speech> {

    @Override
    public int create(Speech speech) throws SQLException {
        String query = "INSERT INTO `Speech` (id_character, id_language) " +
                "VALUES (?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, speech.getCharacterId());
            stmt.setInt(2, speech.getLanguageId());

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
    public void update(Speech speech) throws SQLException {
        String query = "UPDATE `Speech` SET id_character = ?, id_language = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, speech.getCharacterId());
            stmt.setInt(2, speech.getLanguageId());
            stmt.setInt(3, speech.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Speech> read() throws SQLException {
        String query = "SELECT * FROM `Speech`";
        ResultSet resultSet = null;
        Map<Integer, Speech> speechMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Speech speech = new Speech();
                speech.setId(resultSet.getInt("id"));
                speech.setCharacterId(resultSet.getInt("id_character"));
                speech.setLanguageId(resultSet.getInt("id_language"));

                speechMap.put(speech.getId(), speech);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return speechMap;
    }

    @Override
    public Map<Integer, Speech> read(Speech filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Speech` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getCharacterId() != 0) {
            query.append(" AND id_character = ").append(filter.getCharacterId());
        }
        if (filter.getLanguageId() != 0) {
            query.append(" AND id_language = ").append(filter.getLanguageId());
        }

        ResultSet resultSet = null;
        Map<Integer, Speech> speechMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Speech speech = new Speech();
                speech.setId(resultSet.getInt("id"));
                speech.setCharacterId(resultSet.getInt("id_character"));
                speech.setLanguageId(resultSet.getInt("id_language"));

                speechMap.put(speech.getId(), speech);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return speechMap;
    }

    @Override
    public void delete(Speech speech) throws SQLException {
        String query = "DELETE FROM `Speech` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, speech.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }
}
