package com.elixir.model.tables;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class JsonMangerTest {
    @Test
    void read() throws IOException, ParseException {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        jsonObject = (JSONObject) parser.parse(new FileReader(
                "src/main/java/com/elixir/model/tables/json/languages.json"));
        System.out.println(((Map<?, ?>)((List<?>) jsonObject.get("languages")).get(1)).get("name"));
    }
}