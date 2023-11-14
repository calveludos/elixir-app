package com.elixir.dao;

import com.elixir.factory.ConnectionFactory;
import com.elixir.model.*;
import com.elixir.model.Character;
import com.elixir.model.CharacterMaster;
import com.elixir.model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CharacterMasterDAO extends CrudDAO<CharacterMaster> {

    public static class Column {
        String name;
        Class<?> type;

        public Column(String name, Class<?> type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public Class<?> getType() {
            return type;
        }
    }

    @Override
    public int create(CharacterMaster characterMaster) throws SQLException {
        return 0;
    }

    @Override
    public void update(CharacterMaster characterMaster) throws SQLException {

    }

    @Override
    public Map<Integer, CharacterMaster> read(CharacterMaster characterMaster) throws SQLException {
        String query = "SELECT u.id AS user_id, f.id AS folder_id, f.name AS folder_name, c.id AS character_id, c.*, a.id AS attribute_id, a.*, cy.id AS currency_id, cy.*, i.id AS inventory_id, i.*, s.id AS slots_id, s.*, sp.id AS speech_id, sp.*, sl.id AS spell_id, sl.* \n" +
                "FROM `Character` c \n" +
                "LEFT JOIN Folder f ON c.id_folder = f.id\n" +
                "LEFT JOIN `User` u ON f.id_user = u.id\n" +
                "LEFT JOIN Attribute a ON a.id = c.id_attribute\n" +
                "LEFT JOIN Currency cy ON cy.id_character = c.id\n" +
                "LEFT JOIN Inventory i ON i.id_character = c.id\n" +
                "LEFT JOIN Slots s ON s.id_character = c.id\n" +
                "LEFT JOIN Speech sp ON sp.id_character = c.id\n" +
                "LEFT JOIN Spell sl ON sl.id_character = c.id\n" +
                "WHERE u.id = ?;";
        ResultSet resultSet = null;
        List<Map<String, Object>> viewMap = new ArrayList<>();

        Column[] columns = {
                new Column("user_id", Integer.class),
                new Column("folder_name", String.class),
                new Column("folder_id", Integer.class),
                new Column("character_id", Integer.class),
                new Column("id_alignment", Integer.class),
                new Column("id_attribute", Integer.class),
                new Column("id_class", Integer.class),
                new Column("id_race", Integer.class),
                new Column("id_folder", Integer.class),
                new Column("name", String.class),
                new Column("player_name", String.class),
                new Column("experience", Integer.class),
                new Column("height", Integer.class),
                new Column("weight", Integer.class),
                new Column("current_pv", Integer.class),
                new Column("max_pv", Integer.class),
                new Column("class_armor_bonus", Integer.class),
                new Column("apperance", String.class),
                new Column("background", String.class),
                new Column("image_path", String.class),
                new Column("attribute_id", Integer.class),
                new Column("strength", Integer.class),
                new Column("dexterity", Integer.class),
                new Column("constitution", Integer.class),
                new Column("intelligence", Integer.class),
                new Column("wisdom", Integer.class),
                new Column("charisma", Integer.class),
                new Column("currency_id", Integer.class),
                new Column("gold", Integer.class),
                new Column("silver", Integer.class),
                new Column("copper", Integer.class),
                new Column("electrium", Integer.class),
                new Column("platinium", Integer.class),
                new Column("inventory_id", Integer.class),
                new Column("item_id", Integer.class),
                new Column("type_item_id", Integer.class),
                new Column("slots_id", Integer.class),
                new Column("I_level", Integer.class),
                new Column("II_level", Integer.class),
                new Column("III_level", Integer.class),
                new Column("IV_level", Integer.class),
                new Column("V_level", Integer.class),
                new Column("VI_level", Integer.class),
                new Column("VII_level", Integer.class),
                new Column("VIII_level", Integer.class),
                new Column("IX_level", Integer.class),
                new Column("speech_id", Integer.class),
                new Column("id_language", Integer.class),
                new Column("spell_id", Integer.class),
                new Column("type_spell_id", Integer.class)
        };

        try {
            conn = ConnectionFactory.createConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, characterMaster.getFolder().getUserId());
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Map<String, Object> tuple = new HashMap<>();
                for (Column column :
                        columns) {
                    String columnName = column.getName();
                    Class<?> columnClass = column.getType();

                    tuple.put(columnName, columnClass == Integer.class ? resultSet.getInt(columnName) : resultSet.getString(columnName));
                }
                viewMap.add(tuple);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeResources();
            if (resultSet != null) {
                resultSet.close();
            }
        }

        int characterId = -1;
        int inventoryId = -1;
        int speechId = -1;
        int spellId = -1;
        int folderId = -1;
        Folder folder = null;

        Map<Integer, CharacterMaster> characterMasterMap = new HashMap<>();
        CharacterMaster master = null;

        for (Map<String, Object> tuple :
                viewMap) {

            if (folderId != (int) tuple.get("folder_id")){
                folder = new Folder(
                        (int) tuple.get("user_id"),
                        (String) tuple.get("folder_name")
                );

                folder.setId((int) tuple.get("folder_id"));
            }

            if (characterId != (int) tuple.get("character_id")){
                master = new CharacterMaster();
                master.setInventory(new ArrayList<>());
                master.setSpeech(new ArrayList<>());
                master.setSpells(new ArrayList<>());

                Character character = new Character(
                        (int) tuple.get("id_alignment"),
                        (int) tuple.get("id_attribute"),
                        (int) tuple.get("id_class"),
                        (int) tuple.get("id_race"),
                        (int) tuple.get("folder_id"),
                        (String) tuple.get("name"),
                        (String) tuple.get("player_name"),
                        (int) tuple.get("experience"),
                        (int) tuple.get("height"),
                        (int) tuple.get("weight"),
                        (int) tuple.get("current_pv"),
                        (int) tuple.get("max_pv"),
                        (int) tuple.get("class_armor_bonus"),
                        (String) tuple.get("apperance"),
                        (String) tuple.get("background"),
                        (String) tuple.get("image_path")
                );

                character.setId((int) tuple.get("character_id"));
                characterId = character.getId();

                Attribute attribute = new Attribute(
                        (int) tuple.get("strength"),
                        (int) tuple.get("dexterity"),
                        (int) tuple.get("constitution"),
                        (int) tuple.get("intelligence"),
                        (int) tuple.get("wisdom"),
                        (int) tuple.get("charisma")
                );

                attribute.setId((int) tuple.get("attribute_id"));

                Currency currency = new Currency(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("gold"),
                        (int) tuple.get("silver"),
                        (int) tuple.get("copper"),
                        (int) tuple.get("electrium"),
                        (int) tuple.get("platinium")
                );

                currency.setId((int) tuple.get("currency_id"));


                Slots slots = new Slots(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("I_level"),
                        (int) tuple.get("II_level"),
                        (int) tuple.get("III_level"),
                        (int) tuple.get("IV_level"),
                        (int) tuple.get("V_level"),
                        (int) tuple.get("VI_level"),
                        (int) tuple.get("VII_level"),
                        (int) tuple.get("VIII_level"),
                        (int) tuple.get("IX_level")
                );

                slots.setId((int) tuple.get("slots_id"));

                master.setCharacter(character);
                master.setAttribute(attribute);
                master.setCurrency(currency.getId() == 0 ? null : currency);
                master.setSlots(slots.getId() == 0 ? null : slots);

                master.setFolder(folder);

                characterMasterMap.put(master.getId(), master);
            }

            assert master != null;
            if (inventoryId != (int) tuple.get("inventory_id")){
                Inventory inventory = new Inventory(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("item_id"),
                        (int) tuple.get("type_item_id")
                );

                inventory.setId((int) tuple.get("inventory_id"));
                inventoryId = inventory.getId();

                if (!master.getInventory().contains(inventory))
                    master.addInventory(inventory);
            }

            if (speechId != (int) tuple.get("speech_id")){
                Speech speech = new Speech(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("id_language")
                );

                speech.setId((int) tuple.get("speech_id"));

                if (!master.getSpeech().contains(speech))
                    master.addSpeech(speech);
            }


            if (spellId != (int) tuple.get("spell_id")){

                Spell spell = new Spell(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("spell_id"),
                        (int) tuple.get("type_spell_id")
                );

                spell.setSpellId((int) tuple.get("spell_id"));
                spellId = spell.getId();
                if (!master.getSpells().contains(spell))
                    master.addSpell(spell);
            }

        }

        return characterMasterMap;
    }

    @Override
    public Map<Integer, CharacterMaster> read() throws SQLException {
        return null;
    }

    @Override
    public void delete(CharacterMaster characterMaster) throws SQLException {

    }
}
