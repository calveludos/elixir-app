package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.Inventory;
import com.elixir.model.Spell;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SpellDAO extends CrudDAO<Spell> {

    @Override
    public int create(Spell spell) throws SQLException {
        String query = "INSERT INTO `Spell` (character_id, spell_id, type_spell_id) " +
                "VALUES (?, ?, ?)";
        int generatedId = -1;

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, spell.getCharacterId());
            stmt.setInt(2, spell.getSpellId());
            stmt.setInt(3, spell.getTypeSpellId());

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
    public void update(Spell spell) throws SQLException {
        String query = "UPDATE `Spell` SET character_id = ?, spell_id = ?, type_item_id = ? WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);

            stmt.setInt(1, spell.getCharacterId());
            stmt.setInt(2, spell.getSpellId());
            stmt.setInt(3, spell.getTypeSpellId());
            stmt.setInt(4, spell.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }

    @Override
    public Map<Integer, Spell> read() throws SQLException {
        String query = "SELECT * FROM `Spell`";
        ResultSet resultSet = null;
        Map<Integer, Spell> spellMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Spell spell = new Spell();
                spell.setId(resultSet.getInt("id"));
                spell.setCharacterId(resultSet.getInt("character_id"));
                spell.setSpellId(resultSet.getInt("spell_id"));
                spell.setTypeSpellId(resultSet.getInt("type_spell_id"));

                spellMap.put(spell.getId(), spell);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return spellMap;
    }

    @Override
    public Map<Integer, Spell> read(Spell filter) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM `Spell` WHERE 1=1");

        if (filter.getId() != 0) {
            query.append(" AND id = ").append(filter.getId());
        }
        if (filter.getCharacterId() != 0) {
            query.append(" AND character_id = ").append(filter.getCharacterId());
        }
        if (filter.getSpellId() != 0) {
            query.append(" AND spell_id = ").append(filter.getSpellId());
        }
        if (filter.getTypeSpellId() != 0) {
            query.append(" AND type_spell_id = ").append(filter.getTypeSpellId());
        }

        ResultSet resultSet = null;
        Map<Integer, Spell> spellMap = new HashMap<>();

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query.toString());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Spell spell = new Spell();
                spell.setId(resultSet.getInt("id"));
                spell.setCharacterId(resultSet.getInt("character_id"));
                spell.setSpellId(resultSet.getInt("spell_id"));
                spell.setTypeSpellId(resultSet.getInt("type_spell_id"));

                spellMap.put(spell.getId(), spell);
            }

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return spellMap;
    }

    @Override
    public void delete(Spell spell) throws SQLException {
        String query = "DELETE FROM `Spell` WHERE id = ?";

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, spell.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException(e);

        } finally {
            closeResources();
        }
    }
}
