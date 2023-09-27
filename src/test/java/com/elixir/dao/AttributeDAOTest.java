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
<<<<<<< HEAD
        int id = attributeDAO.create(attribute);
        System.out.println(id);
=======
        attributeDAO.create(attribute);
        id = attribute.getId();

>>>>>>> 2126942495681685b6b1766c1a0267f087f4d6f0
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