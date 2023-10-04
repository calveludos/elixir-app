package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDAO extends CrudDAO<Currency> {

    @Override
    public int create(Currency currency) throws SQLException {
        String query = "INSERT INTO Currency (id_character,gold, silver, copper, electrium, platinium) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, currency.getIdCharacter());
            stmt.setDouble(2, currency.getGold());
            stmt.setDouble(3, currency.getSilver());
            stmt.setDouble(4, currency.getCopper());
            stmt.setDouble(5, currency.getElectrium());
            stmt.setDouble(6, currency.getPlatinium());

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
    public void update(Currency currency) throws SQLException {
        String query = "UPDATE Currency SET id_character = ?, gold = ?, silver = ?, copper = ?, electrium = ?, platinium = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, currency.getIdCharacter());
            stmt.setDouble(2, currency.getGold());
            stmt.setDouble(3, currency.getSilver());
            stmt.setDouble(4, currency.getCopper());
            stmt.setDouble(5, currency.getElectrium());
            stmt.setDouble(6, currency.getPlatinium());
            stmt.setInt(7, currency.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Currency> read() throws SQLException {
        String query = "SELECT * FROM Currency";
        ResultSet resultSet = null;
        Map<Integer, Currency> currencyMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Currency currency = new Currency();
                currency.setId(resultSet.getInt("id"));
                currency.setIdCharacter(resultSet.getInt("id_character"));
                currency.setGold(resultSet.getDouble("gold"));
                currency.setSilver(resultSet.getDouble("silver"));
                currency.setCopper(resultSet.getDouble("copper"));
                currency.setElectrium(resultSet.getDouble("electrium"));
                currency.setPlatinium(resultSet.getDouble("platinium"));

                currencyMap.put(currency.getId(), currency);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return currencyMap;
    }

    public Map<Integer, Currency> read(Currency filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Currency` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getIdCharacter() != 0) {
            query.append(" AND id_character = ").append(filter.getIdCharacter());
        }
        if (filter.getGold() != 0) {
            query.append(" AND gold = ").append(filter.getGold());
        }
        if (filter.getSilver() != 0) {
            query.append(" AND silver = ").append(filter.getSilver());
        }
        if (filter.getCopper() != 0) {
            query.append(" AND copper = ").append(filter.getCopper());
        }
        if (filter.getElectrium() != 0) {
            query.append(" AND electrium = ").append(filter.getElectrium());
        }
        if (filter.getPlatinium() != 0) {
            query.append(" AND platinium = ").append(filter.getPlatinium());
        }


        ResultSet resultSet = null;
        Map<Integer, Currency> currencyMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Currency currency = new Currency();
                currency.setId(resultSet.getInt("id"));
                currency.setIdCharacter(resultSet.getInt("id_character"));
                currency.setGold(resultSet.getDouble("gold"));
                currency.setSilver(resultSet.getDouble("silver"));
                currency.setCopper(resultSet.getDouble("copper"));
                currency.setElectrium(resultSet.getDouble("electrium"));
                currency.setPlatinium(resultSet.getDouble("platinium"));



                currencyMap.put(currency.getId(), currency);
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
    public void delete(Currency currency) throws SQLException {
        String query = "DELETE FROM Currency WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, currency.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
        }
    }
}
