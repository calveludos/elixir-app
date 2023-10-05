package com.elixir.model;

import java.io.Serializable;

public class Character implements Serializable {
    private int id;
    private int alignmentId;
    private int attributeId;
    private int classId;
    private int raceId;
    private int folderId;
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

    public int level;

    public Character() {
        // Construtor vazio
    }

    public Character(int alignmentId, int attributeId, int classId, int raceId, int folderId, String name, String playerName, int experience, int height, int weight, int currentPv, int maxPv, int classArmorBonus, String appearance, String background, String imagePath) {
        this.alignmentId = alignmentId;
        this.attributeId = attributeId;
        this.classId = classId;
        this.raceId = raceId;
        this.folderId = folderId;
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

    public int getAlignmentId() {
        return alignmentId;
    }

    public void setAlignmentId(int alignmentId) {
        this.alignmentId = alignmentId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
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
                ", alignmentId=" + alignmentId +
                ", attributeId=" + attributeId +
                ", classId=" + classId +
                ", raceId=" + raceId +
                ", folderId=" + folderId +
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
