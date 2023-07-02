package com.elixir.model;

public class Race {
    private int id;
    private String name;
    private int movement;
    private String size;

    public Race() {
    }

    public Race(String name, int movement, String size) {
        this.name = name;
        this.movement = movement;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
