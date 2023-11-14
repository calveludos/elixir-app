package com.elixir.model;

import java.io.Serializable;

public class Spell implements Serializable {
    private int id;
    private int characterId;
    private int spellId;
    private int typeSpellId;

    public Spell() {
    }

    public Spell(int characterId, int spellId, int typeSpellId) {
        this.characterId = characterId;
        this.spellId = spellId;
        this.typeSpellId = typeSpellId;
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

    public int getSpellId() {
        return spellId;
    }

    public void setSpellId(int spellId) {
        this.spellId = spellId;
    }

    public int getTypeSpellId() {
        return typeSpellId;
    }

    public void setTypeSpellId(int typeSpellId) {
        this.typeSpellId = typeSpellId;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "id=" + id +
                ", characterId=" + characterId +
                ", spellId=" + spellId +
                ", typeSpellId=" + typeSpellId +
                '}';
    }
}
