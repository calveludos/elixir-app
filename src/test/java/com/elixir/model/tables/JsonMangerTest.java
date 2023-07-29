package com.elixir.model.tables;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonMangerTest {
    @Test
    void read() {
        JSONObject data = JsonManger.readTables();
        System.out.println(data.toString());
    }
}