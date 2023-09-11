package com.elixir;

import com.elixir.manager.ObjectSaveManager;
import org.junit.jupiter.api.Test;

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
        reader.printMap();
    }
}