package com.elixir.model.tables;

import java.util.HashMap;
import java.util.Map;

public class ExperienceTable {
    public static class Clerigo {
        // ...
    }

    public static Map<Integer, Integer> table = new HashMap<>();

    public static void main(String[] args) {
        int x = 0;
        for (int i = 0; i < 30; i++) {
            if (i == 2) {
                x = 1500;
            }
            if (i > 2 && i < 8) {
                x *= 2;
            }

            table.put(i, x);
            System.out.println(table.get(i));
        }
    }
}
