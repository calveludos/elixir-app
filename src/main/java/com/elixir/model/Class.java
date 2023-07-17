package com.elixir.model;

public class Class {

    private int id;
    private String name;
    private int id_specialization;
    private String dice_pv;


    public Class(){
    }

    public Class(String name, int id_specialization, String dice_pv ){
        this.name = name;
        this.id_specialization = id_specialization;
        this.dice_pv = dice_pv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getId_specialization() {return id_specialization;}
    public void setId_specialization(int id_specialization) {
        this.id_specialization = id_specialization;
    }

    public String getDice_pv() {return dice_pv;}
    public void setDice_pv(String dice_pv) {this.dice_pv = dice_pv;}
}


