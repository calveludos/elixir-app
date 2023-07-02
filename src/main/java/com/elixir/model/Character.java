package com.elixir.model;

import java.util.Objects;

public class Character extends Model{
    private int id;

    private String race;
    private int attributeId;
    private String name;
    private int experience;
    private int height;
    private int weight;
    private int currentPv;
    private int maxPv;

    // Construtor
    public Character() {
    }

    public Character(int attributeId, String race, String name, int experience, int height, int weight, int currentPv, int maxPv) {
        setAttributeId(attributeId);
        setRace(race);
        setName(name);
        setExperience(experience);
        setHeight(height);
        setWeight(weight);
        setCurrentPv(currentPv);
        setMaxPv(maxPv);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public String getRace() {return race;}

    public void setRace(String race) {
        if (!Objects.equals(race, "humano") && !Objects.equals(race, "anão") && !Objects.equals(race, "elfo") && !Objects.equals(race, "halfing")){
            throw new IllegalArgumentException("Essa raça não existe no Old Dragon RPG.");
        }
        this.race = race;
    }

    public String getName() {return name;}

    public void setName(String name) {
        if(name.length() < 1){
            throw new IllegalArgumentException("Seu nome precisa contar pelo menos um caractere.");
        }
        this.name = name;
    }

    public int getExperience() {return experience;}

    public void setExperience(int experience) {
        if(experience < 0 ){
            throw new IllegalArgumentException("A experiência do personagem não pode ser negativa ou menor que zero.");
        }
        this.experience = experience;
    }

    public int getHeight() {return height;}

    public void setHeight(int height) { // Podemos implementar mapas para otimizar este setter.
        if(Objects.equals(race, "humano") && (height < 0 || height >200)){
            throw new IllegalArgumentException("A altura do humano tem que ser de até 2 metros e não pode ser negativa.");
        }
        if(Objects.equals(race, "anão") && (height < 0 || height >150)){
            throw new IllegalArgumentException("A altura do anão tem que ser de até 1.5 metro e não pode ser negativa.");
        }
        if(Objects.equals(race, "elfo") && (height < 0 || height >170)){
            throw new IllegalArgumentException("A altura do anão tem que ser de até 1.7 metro e não pode ser negativa.");
        }
        if(Objects.equals(race, "halfing") && (height < 0 || height >90)){
            throw new IllegalArgumentException("A altura do anão tem que ser de até 0.9 metro e não pode ser negativa.");
        }
        this.height = height;
    }

    public int getWeight() {return weight;}

    public void setWeight(int weight) {
        if(weight < 0 ){
            throw new IllegalArgumentException("O peso do personagem não pode ser negativo ou menor que zero.");
        }
        this.weight = weight;
    }

    public int getCurrentPv() {return currentPv;}

    //Current PV pode ser maior que o Max pv e pode ser negativo também
    public void setCurrentPv(int currentPv) {
        this.currentPv = currentPv;
    }

    public int getMaxPv() {return maxPv;}

    public void setMaxPv(int maxPv) {
        if(maxPv < 0){
            throw new IllegalArgumentException("Um personagem já morto não é válido"); // Tecnicamente é possivel ter zero de vida mas não é jogável
        }
        this.maxPv = maxPv;
    }
}

