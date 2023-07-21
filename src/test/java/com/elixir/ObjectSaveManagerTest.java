package com.elixir;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ObjectSaveManagerTest {
    @Test
    void saveObjects() {
        ObjectSaveManager<String> saver = new ObjectSaveManager<>();
        saver.saveObject("String 1", "string 1");
        saver.saveObject("String 2", "string 2");
        saver.saveObject("String 3", "string 3");
    }

    @Test
    void readObjects() {
        ObjectSaveManager<String> reader = new ObjectSaveManager<>();
        Object map = reader.getObject("String 1");
        System.out.println(map.toString());
    }
}