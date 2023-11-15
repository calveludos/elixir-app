package com.teamvectora.elixirapi.model;

import java.io.Serializable;

public class Currency implements Serializable {
    private int id;
    private int characterId;
    private int gold;
    private int silver;
    private int copper;
    private int electrium;
    private int platinium;

    public Currency() {
    }

    public Currency(int characterId, int gold, int silver, int copper, int electrium, int platinium) {
        this.characterId = characterId;
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
        this.electrium = electrium;
        this.platinium = platinium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getCopper() {
        return copper;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    public int getElectrium() {
        return electrium;
    }

    public void setElectrium(int electrium) {
        this.electrium = electrium;
    }

    public int getPlatinium() {
        return platinium;
    }

    public void setPlatinium(int platinium) {
        this.platinium = platinium;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", characterId=" + characterId +
                ", gold=" + gold +
                ", silver=" + silver +
                ", copper=" + copper +
                ", electrium=" + electrium +
                ", platinium=" + platinium +
                '}';
    }
}
