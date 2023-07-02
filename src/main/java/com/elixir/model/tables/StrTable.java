package com.elixir.model.tables;

import java.util.HashMap;
import java.util.Map;
public class StrTable {
    public static void main(String[] args) {

        Map<Integer, Integer> strength = new HashMap<>();

        int ajusteAtaqueDano = -5;

        for(int i = 1; i < 30; i++){
            strength.put(i,ajusteAtaqueDano);
            if(i % 2 != 0 ){
                ajusteAtaqueDano+=1;
            }
        }

        for(int key : strength.keySet()){
            System.out.print("FOR " + key + " Ajuste de ataque e de dano " + strength.get((key))+ "\n");
        }

    }

}
