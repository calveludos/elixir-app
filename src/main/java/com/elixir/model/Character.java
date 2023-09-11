package com.elixir.model;

import java.io.Serializable;
import java.util.Objects;

public class Character implements Serializable {
    private int id;
    private int raceId;
    private int attributeId;
    private int alignmentId;
    private int classId;
    private String name;
    private String playerName;
    private int experience;
    private int height;
    private int weight;
    private int currentPv;
    private int maxPv;
    private int currencyId;
    private int slots;
    private String appearance;
    private int classArmorBonus;
    private String background;

    // Construtor
    public Character() {
    }

    public Character(int attributeId, int raceId, int alignmentId, int classId, String name, String playerName, int experience, int height, int weight, int currentPv, int maxPv, int currencyId, int slots, String appearance, int classArmorBonus, String background) {
        setAttributeId(attributeId);
        setRaceId(raceId);
        setAlignmentId(alignmentId);
        setClassId(classId);
        setName(name);
        setPlayerName(playerName);
        setExperience(experience);
        setHeight(height);
        setWeight(weight);
        setCurrentPv(currentPv);
        setMaxPv(maxPv);
        setCurrencyId(currencyId);
        setSlots(slots);
        setAppearance(appearance);
        setClassArmorBonus(classArmorBonus);
        setBackground(background);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        if (raceId < 1 || raceId > 4) {
            throw new IllegalArgumentException("Essa raça não existe no Old Dragon RPG.");
        }
        this.raceId = raceId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public int getAlignmentId() {
        return alignmentId;
    }

    public void setAlignmentId(int alignmentId) {
        this.alignmentId = alignmentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException("Seu nome precisa contar pelo menos um caractere.");
        }
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
        if (experience < 0) {
            throw new IllegalArgumentException("A experiência do personagem não pode ser negativa ou menor que zero.");
        }
        this.experience = experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (raceId == 1 && (height < 0 || height > 200)) {
            throw new IllegalArgumentException("A altura do humano tem que ser de até 2 metros e não pode ser negativa.");
        }
        if (raceId == 2 && (height < 0 || height > 150)) {
            throw new IllegalArgumentException("A altura do anão tem que ser de até 1.5 metro e não pode ser negativa.");
        }
        if (raceId == 3 && (height < 0 || height > 170)) {
            throw new IllegalArgumentException("A altura do elfo tem que ser de até 1.7 metro e não pode ser negativa.");
        }
        if (raceId == 4 && (height < 0 || height > 90)) {
            throw new IllegalArgumentException("A altura do halfling tem que ser de até 0.9 metro e não pode ser negativa.");
        }
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("O peso do personagem não pode ser negativo ou menor que zero.");
        }
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
        if (maxPv < 0) {
            throw new IllegalArgumentException("Um personagem já morto não é válido");
        }
        this.maxPv = maxPv;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public int getClassArmorBonus() {
        return classArmorBonus;
    }

    public void setClassArmorBonus(int classArmorBonus) {
        this.classArmorBonus = classArmorBonus;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id &&
                raceId == character.raceId &&
                attributeId == character.attributeId &&
                alignmentId == character.alignmentId &&
                classId == character.classId &&
                experience == character.experience &&
                height == character.height &&
                weight == character.weight &&
                currentPv == character.currentPv &&
                maxPv == character.maxPv &&
                currencyId == character.currencyId &&
                slots == character.slots &&
                Objects.equals(name, character.name) &&
                Objects.equals(appearance, character.appearance);
    }

    public boolean isDateNull(){
        if (name == null && alignmentId == -1 && appearance == null && experience == -1){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, raceId, attributeId, alignmentId, classId, name, experience, height, weight, currentPv, maxPv, currencyId, slots, appearance);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", raceId=" + raceId +
                ", attributeId=" + attributeId +
                ", alignmentId=" + alignmentId +
                ", classId=" + classId +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", height=" + height +
                ", weight=" + weight +
                ", currentPv=" + currentPv +
                ", maxPv=" + maxPv +
                ", currencyId=" + currencyId +
                ", slots=" + slots +
                ", appearance='" + appearance + '\'' +
                ", classArmorBonus=" + classArmorBonus +
                '}';
    }

}
