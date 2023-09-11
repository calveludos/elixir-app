package com.elixir.manager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class JsonManger {
    public static JSONObject read(String name) throws IOException, ParseException {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        jsonObject = (JSONObject) parser.parse(new FileReader(
                "src/main/java/com/elixir/model/tables/json/" + name + ".json"));
        return jsonObject;
    }

    public static void write(JSONObject jsonObject, String name) throws IOException {
        FileWriter writeFile = null;

        writeFile = new FileWriter("src/main/java/com/elixir/model/tables/json/" + name + ".json");

        writeFile.write(jsonObject.toJSONString());
        writeFile.close();
    }

}
