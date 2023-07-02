package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RaceDAO extends CrudDAO<Race> {

    @Override
    public void create(Race race) throws SQLException {
        String query = "INSERT INTO Race (name, movement, size) VALUES (?, ?, ?)";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, race.getName());
            stmt.setInt(2, race.getMovement());
            stmt.setString(3, race.getSize());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public void update(Race race) throws SQLException {
        String query = "UPDATE Race SET name = ?, movement = ?, size = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, race.getName());
            stmt.setInt(2, race.getMovement());
            stmt.setString(3, race.getSize());
            stmt.setInt(4, race.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Race> read() throws SQLException {
        String query = "SELECT * FROM Race";
        ResultSet resultSet = null;
        Map<Integer, Race> raceMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Race race = new Race();
                race.setId(resultSet.getInt("id"));
                race.setName(resultSet.getString("name"));
                race.setMovement(resultSet.getInt("movement"));
                race.setSize(resultSet.getString("size"));

                raceMap.put(race.getId(), race);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return raceMap;
    }

    @Override
    public void delete(Race race) throws SQLException {
        String query = "DELETE FROM Race WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, race.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }


}
