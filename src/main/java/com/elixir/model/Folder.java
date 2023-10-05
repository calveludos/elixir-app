package com.elixir.model;

import java.io.Serializable;

public class Folder implements Serializable {
    private int id;
    private int userId;
    private String name;

    public Folder() {
    }

    public Folder(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
