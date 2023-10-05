package com.elixir.model;

import java.io.Serializable;

public class Inventory implements Serializable {
    private int id;
    private int characterId;
    private int itemId;
    private int typeItemId;

    public Inventory() {
    }

    public Inventory(int characterId, int itemId, int typeItemId) {
        this.characterId = characterId;
        this.itemId = itemId;
        this.typeItemId = typeItemId;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTypeItemId() {
        return typeItemId;
    }

    public void setTypeItemId(int typeItemId) {
        this.typeItemId = typeItemId;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", characterId=" + characterId +
                ", itemId=" + itemId +
                ", typeItemId=" + typeItemId +
                '}';
    }
}
