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
        String query = "INSERT INTO Currency (gold, silver, copper, electrium, platinium) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, currency.getGold());
            stmt.setDouble(2, currency.getSilver());
            stmt.setDouble(3, currency.getCopper());
            stmt.setDouble(4, currency.getElectrium());
            stmt.setDouble(5, currency.getPlatinium());

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
        String query = "UPDATE Currency SET gold = ?, silver = ?, copper = ?, electrium = ?, platinium = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setDouble(1, currency.getGold());
            stmt.setDouble(2, currency.getSilver());
            stmt.setDouble(3, currency.getCopper());
            stmt.setDouble(4, currency.getElectrium());
            stmt.setDouble(5, currency.getPlatinium());
            stmt.setInt(6, currency.getId());

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
