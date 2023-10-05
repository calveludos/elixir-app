package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Currency;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDAO extends CrudDAO<Currency> {

    @Override
    public int create(Currency currency) throws SQLException {
        String query = "INSERT INTO `Currency` (id_character, gold, silver, copper, electrium, platinium) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, currency.getCharacterId());
            stmt.setShort(2, (short) currency.getGold());
            stmt.setShort(3, (short) currency.getSilver());
            stmt.setShort(4, (short) currency.getCopper());
            stmt.setShort(5, (short) currency.getElectrium());
            stmt.setShort(6, (short) currency.getPlatinium());

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
    public void update(Currency currency) throws SQLException {
        String query = "UPDATE `Currency` SET id_character = ?, gold = ?, silver = ?, copper = ?, electrium = ?, platinium = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, currency.getCharacterId());
            stmt.setShort(2, (short) currency.getGold());
            stmt.setShort(3, (short) currency.getSilver());
            stmt.setShort(4, (short) currency.getCopper());
            stmt.setShort(5, (short) currency.getElectrium());
            stmt.setShort(6, (short) currency.getPlatinium());
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
        String query = "SELECT * FROM `Currency`";
        ResultSet resultSet = null;
        Map<Integer, Currency> currencyMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Currency currency = new Currency();
                currency.setId(resultSet.getInt("id"));
                currency.setCharacterId(resultSet.getInt("id_character"));
                currency.setGold(resultSet.getShort("gold"));
                currency.setSilver(resultSet.getShort("silver"));
                currency.setCopper(resultSet.getShort("copper"));
                currency.setElectrium(resultSet.getShort("electrium"));
                currency.setPlatinium(resultSet.getShort("platinium"));

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
    public Map<Integer, Currency> read(Currency filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Currency` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getCharacterId() != 0) {
            query.append(" AND id_character = ").append(filter.getCharacterId());
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
                currency.setCharacterId(resultSet.getInt("id_character"));
                currency.setGold(resultSet.getShort("gold"));
                currency.setSilver(resultSet.getShort("silver"));
                currency.setCopper(resultSet.getShort("copper"));
                currency.setElectrium(resultSet.getShort("electrium"));
                currency.setPlatinium(resultSet.getShort("platinium"));

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
        String query = "DELETE FROM `Currency` WHERE id = ?";

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
