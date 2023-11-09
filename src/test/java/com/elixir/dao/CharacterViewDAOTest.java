package com.elixir.dao;

import com.elixir.model.*;
import com.elixir.model.Character;
import com.elixir.model.Currency;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CharacterViewDAOTest {
    @Test
    public void readeTest() throws SQLException {
        CharacterViewDAO viewDAO = new CharacterViewDAO();
        List<Map<String, Object>> table = viewDAO.readList(6);

        int characterId = -1;
        int inventoryId = -1;
        int speechId = -1;

        Map<Integer, CharacterMaster> characterMasterMap = new HashMap<>();
        CharacterMaster master = null;
        for (Map<String, Object> tuple :
                table) {

            if (characterId != (int) tuple.get("character_id")){
                master = new CharacterMaster();
                master.setInventory(new ArrayList<>());
                master.setSpeech(new ArrayList<>());

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
                        (String) tuple.get("appearance"),
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
            }

            if (inventoryId != (int) tuple.get("inventory_id")){

                Inventory inventory = new Inventory(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("item_id"),
                        (int) tuple.get("type_item_id")
                );

                inventory.setId((int) tuple.get("inventory_id"));
                inventoryId = inventory.getId();

                assert master != null;
                master.addInventory(inventory);
            }

            if (speechId != (int) tuple.get("speech_id")){

                Speech speech = new Speech(
                        (int) tuple.get("character_id"),
                        (int) tuple.get("id_language")
                );

                speech.setId((int) tuple.get("speech_id"));
                speechId = speech.getId();


            }


        }


        for (Map<String, Object> line:
                table) {
            for (Object item :
                    line.values()) {
                System.out.print(item + " | ");
            }
            System.out.println();
        }
    }

}