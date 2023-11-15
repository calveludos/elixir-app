package com.teamvectora.elixirapi.model;

import java.io.Serializable;

public class Slots implements Serializable {
    private int id;
    private int characterId;
    private int iLevel;
    private int iiLevel;
    private int iiiLevel;
    private int ivLevel;
    private int vLevel;
    private int viLevel;
    private int viiLevel;
    private int viiiLevel;
    private int ixLevel;

    public Slots() {
    }

    public Slots(int characterId, int iLevel, int iiLevel, int iiiLevel, int ivLevel, int vLevel, int viLevel, int viiLevel, int viiiLevel, int ixLevel) {
        this.characterId = characterId;
        this.iLevel = iLevel;
        this.iiLevel = iiLevel;
        this.iiiLevel = iiiLevel;
        this.ivLevel = ivLevel;
        this.vLevel = vLevel;
        this.viLevel = viLevel;
        this.viiLevel = viiLevel;
        this.viiiLevel = viiiLevel;
        this.ixLevel = ixLevel;
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

    public int getILevel() {
        return iLevel;
    }

    public void setILevel(int iLevel) {
        this.iLevel = iLevel;
    }

    public int getIiLevel() {
        return iiLevel;
    }

    public void setIiLevel(int iiLevel) {
        this.iiLevel = iiLevel;
    }

    public int getIiiLevel() {
        return iiiLevel;
    }

    public void setIiiLevel(int iiiLevel) {
        this.iiiLevel = iiiLevel;
    }

    public int getIvLevel() {
        return ivLevel;
    }

    public void setIvLevel(int ivLevel) {
        this.ivLevel = ivLevel;
    }

    public int getVLevel() {
        return vLevel;
    }

    public void setVLevel(int vLevel) {
        this.vLevel = vLevel;
    }

    public int getViLevel() {
        return viLevel;
    }

    public void setViLevel(int viLevel) {
        this.viLevel = viLevel;
    }

    public int getViiLevel() {
        return viiLevel;
    }

    public void setViiLevel(int viiLevel) {
        this.viiLevel = viiLevel;
    }

    public int getViiiLevel() {
        return viiiLevel;
    }

    public void setViiiLevel(int viiiLevel) {
        this.viiiLevel = viiiLevel;
    }

    public int getIxLevel() {
        return ixLevel;
    }

    public void setIxLevel(int ixLevel) {
        this.ixLevel = ixLevel;
    }

    @Override
    public String toString() {
        return "Slots{" +
                "id=" + id +
                ", characterId=" + characterId +
                ", iLevel=" + iLevel +
                ", iiLevel=" + iiLevel +
                ", iiiLevel=" + iiiLevel +
                ", ivLevel=" + ivLevel +
                ", vLevel=" + vLevel +
                ", viLevel=" + viLevel +
                ", viiLevel=" + viiLevel +
                ", viiiLevel=" + viiiLevel +
                ", ixLevel=" + ixLevel +
                '}';
    }
}
