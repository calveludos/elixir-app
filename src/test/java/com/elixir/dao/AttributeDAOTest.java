package com.elixir.dao;

import com.elixir.model.Attribute;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class AttributeDAOTest {
    AttributeDAO attributeDAO = new AttributeDAO();
    int id;

    @Test
    void create() throws SQLException {
        Attribute attribute = new Attribute(10, 10, 14, 16, 15, 12);
        id = attributeDAO.create(attribute);
        System.out.println(id);

        read();
    }

    @Test
    void update() throws SQLException{
        Map<Integer, Attribute> attributeMap = attributeDAO.read();
        Attribute attribute = attributeMap.get(id);
        attribute.setStrength(18);
        attributeDAO.update(attribute);

        read();
    }

    @Test
    void read() throws SQLException{
        Map<Integer, Attribute> result = attributeDAO.read();
        for (int key :
                result.keySet()) {
        System.out.println(key + result.get(key).toString());
        }
    }

    @Test
    void delete() throws SQLException {
        Map<Integer, Attribute> attributeMap = attributeDAO.read();
        Attribute attribute = attributeMap.get(id);
        attributeDAO.delete(attribute);

        read();
    }
}