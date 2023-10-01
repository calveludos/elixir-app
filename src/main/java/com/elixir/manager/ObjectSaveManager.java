package com.elixir.manager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ObjectSaveManager {

    private Map<String, Object> map;

    public ObjectSaveManager() {
        File file = new File("object.bin");
        if (!file.exists()) {
            map = new HashMap<>();
            saveMapToFile();
            System.out.println("Map criado");
        } else {
            map = readMap();
            System.out.println("Map lido");
        }
    }

    public void saveObject(String key, Object object) {
        map.put(key, object);
        saveMapToFile();
        System.out.println("Objeto salvo com a chave: " + key);
    }

    public Object getObject(String key) {
        return map.get(key);
    }

    public void removeObject(String key) {
        map.remove(key);
        saveMapToFile();
        System.out.println("Objeto removido com a chave: " + key);
    }

    public void cleanObjects() {
        map.clear();
        saveMapToFile();
        System.out.println("Map limpo");
    }

    private void saveMapToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("object.bin"))) {
            outputStream.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> readMap() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("object.bin"))) {
            map = (Map<String, Object>) inputStream.readObject();
            System.out.println("Map de objetos carregado");
            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("mapa nulo");
        return new HashMap<>();
    }
    public void printMap() {
        System.out.println("Conteúdo do mapa:");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("Chave: " + key + ", Valor: " + value);
        }
    }
}
