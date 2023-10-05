package com.elixir.model;

import java.io.Serializable;

public class Character implements Serializable {
    private int id;
    private int idAlignment;
    private int idAttribute;
    private int idClass;
    private int idRace;
    private int idFolder;
    private String name;
    private String playerName;
    private int experience;
    private int height;
    private int weight;
    private int currentPv;
    private int maxPv;
    private int classArmorBonus;
    private String appearance;
    private String background;
    private String imagePath;

    public Character() {
        // Construtor vazio
    }

    public Character(int idAlignment, int idAttribute, int idClass, int idRace, int idFolder, String name, String playerName, int experience, int height, int weight, int currentPv, int maxPv, int classArmorBonus, String appearance, String background, String imagePath) {
        this.idAlignment = idAlignment;
        this.idAttribute = idAttribute;
        this.idClass = idClass;
        this.idRace = idRace;
        this.idFolder = idFolder;
        this.name = name;
        this.playerName = playerName;
        this.experience = experience;
        this.height = height;
        this.weight = weight;
        this.currentPv = currentPv;
        this.maxPv = maxPv;
        this.classArmorBonus = classArmorBonus;
        this.appearance = appearance;
        this.background = background;
        this.imagePath = imagePath;
    }

    // Getters e Setters para todos os atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlignment() {
        return idAlignment;
    }

    public void setIdAlignment(int idAlignment) {
        this.idAlignment = idAlignment;
    }

    public int getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(int idAttribute) {
        this.idAttribute = idAttribute;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public int getIdFolder() {
        return idFolder;
    }

    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
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

    public int getClassArmorBonus() {
        return classArmorBonus;
    }

    public void setClassArmorBonus(int classArmorBonus) {
        this.classArmorBonus = classArmorBonus;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", idAlignment=" + idAlignment +
                ", idAttribute=" + idAttribute +
                ", idClass=" + idClass +
                ", idRace=" + idRace +
                ", idFolder=" + idFolder +
                ", name='" + name + '\'' +
                ", playerName='" + playerName + '\'' +
                ", experience=" + experience +
                ", height=" + height +
                ", weight=" + weight +
                ", currentPv=" + currentPv +
                ", maxPv=" + maxPv +
                ", classArmorBonus=" + classArmorBonus +
                ", appearance='" + appearance + '\'' +
                ", background='" + background + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
