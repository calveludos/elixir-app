package com.elixir.model;

public class Folder {
    private int id;
    private int id_user;
    private String name;
    private String color;

    public Folder() {
    }

     public Folder(int id_user, String name, String color) {
        this.id_user = id_user;
        this.name = name;
        this.color = color;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user() {
        this.id_user = id_user
    }

    public int getName() {
        return name;
    }

    public void setName() {
        this.name = name
    }

    public int getColor() {
        return color;
    }

    public void setColor() {
        this.color = color
    }
}