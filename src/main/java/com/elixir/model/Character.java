package com.elixir.model;
public class Character extends Model{
    private int id;
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

    public Character(int attributeId, String name, int experience, int height, int weight, int currentPv, int maxPv) {
        setAttributeId(attributeId);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCurrentPv() {
        return currentPv;
    }

    public void setCurrentPv(int currentPv) {
        this.currentPv = currentPv;
    }

    public int getMaxPv() {
        return maxPv;
    }

    public void setMaxPv(int maxPv) {
        this.maxPv = maxPv;
    }
}

