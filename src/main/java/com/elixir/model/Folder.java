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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}