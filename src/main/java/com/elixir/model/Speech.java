package com.elixir.model;

import java.io.Serializable;

public class Speech implements Serializable {
    private int id;
    private int characterId;
    private int languageId;

    public Speech() {
    }

    public Speech(int characterId, int languageId) {
        this.characterId = characterId;
        this.languageId = languageId;
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

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", characterId=" + characterId +
                ", languageId=" + languageId +
                '}';
    }
}
