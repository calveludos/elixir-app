package com.elixir.model;

import java.io.Serializable;

public class Inventory implements Serializable {
    private int id;
    private int id_character;
    private int id_item;
    private int type_item_id;

    public Inventory(){
    }

    public Inventory(int id_character, int id_item, int type_item_id){
        setId_character(id_character);
        setId_item(id_item);
        setType_item_id(type_item_id);
    }

    public int getId(){
        return id;
    }

    public int getId_character(){
        return id_character;
    }

    public int getId_item(){
        return id_item;
    }

    public int getType_item_id(){
        return type_item_id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setId_character(int id_character){
        this.id_character = id_character;
    }

    public void setId_item(int id_item){
        this.id_item = id_item;
    }

    public void setType_item_id(int type_item_id){
        this.type_item_id = type_item_id;
    }

     @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", id_character=" + id_character +
                ", id_item=" + id_item +
                ", type_item_id=" + type_item_id +
                '}';
    }

}