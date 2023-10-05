package com.elixir.manager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JsonManger {
    public static JSONObject read(String name) throws IOException, ParseException {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        jsonObject = (JSONObject) parser.parse(new FileReader(
                "src/main/java/com/elixir/model/tables/json/" + name + ".json"));
        return jsonObject;
    }

    public static Object get(String name, String[] path) throws IOException, ParseException {
        JSONObject jsonObject = read(name);
        Object ret = null;
        for (int i = 0; i < path.length; i++) {
            String p = path[i];
            System.out.println("get " + p);
            System.out.println(jsonObject.toString());
            if (i < path.length-1) {
                jsonObject = (JSONObject) jsonObject.get(p);
            } else {
                ret = jsonObject.get(p);
            }
        }
        return ret;
    }

    public static Object get(String name, List<Object> path) throws IOException, ParseException {
        JSONObject jsonObject = read(name);
        Object ret = null;
        for (int i = 0; i < path.size(); i++) {
            Object p = path.get(i);
            if (i < path.size()-1) {
                if (p instanceof String) {
                    jsonObject = (JSONObject) jsonObject.get(p);
                } else if (p instanceof Tuple) {
                    Tuple<String, Integer> tuple = (Tuple<String, Integer>) p;
                    System.out.println(jsonObject.get(tuple.getFirst()).toString());
                    System.out.println(jsonObject.get(tuple.getFirst()).getClass());
                    JSONArray jsonList = (JSONArray) jsonObject.get(tuple.getFirst());
                    System.out.println(jsonList);
                    jsonObject = (JSONObject) jsonList.get(tuple.getSecond() - 1);
                }
            } else {
                if (p instanceof String) {
                    ret = jsonObject.get(p);
                } else if (p instanceof Tuple) {
                    ret = ((Object[]) jsonObject.get(((Tuple<?, ?>) p).getFirst()))[(int) ((Tuple<?, ?>) p).getSecond()];
                }
            }
            System.out.println("get " + p);
            System.out.println(jsonObject.toString());
        }
        return ret;
    }

    public static void write(JSONObject jsonObject, String name) throws IOException {
        FileWriter writeFile = null;

        writeFile = new FileWriter("src/main/java/com/elixir/model/tables/json/" + name + ".json");

        writeFile.write(jsonObject.toJSONString());
        writeFile.close();
    }

}
