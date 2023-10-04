package com.elixir.model;

import java.io.Serializable;
import java.util.Objects;

public class Currency implements Serializable{
    private int id;
    private int id_character;
    private double gold;
    private double silver;
    private double copper;
    private double electrium;
    private double platinium;


    public Currency(){
    }

    public Currency(int id_character,double gold, double silver, double copper, double electrium, double platinium) {
        setIdCharacter(id_character);
        setGold(gold);
        setSilver(silver);
        setCopper(copper);
        setElectrium(electrium);
        setPlatinium(platinium);
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getIdCharacter() {return id_character;}

    public void setIdCharacter(int id_character) {this.id_character =id_character;}

    public double getGold() {return gold;}

    public void setGold(double gold) {this.gold = gold;}

    public double getSilver() {return silver;}

    public void setSilver(double silver) {this.silver = silver;}

    public double getCopper() {return copper;}

    public void setCopper(double copper) {this.copper = copper;}

    public double getElectrium() {return electrium;}

    public void setElectrium(double electrium) {this.electrium = electrium;}

    public double getPlatinium() {return platinium;}

    public void setPlatinium(double platinium) {this.platinium = platinium;}

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", id_character=" + id_character+
                ", gold=" + gold +
                ", silver=" + silver +
                ", copper=" + copper +
                ", electrium=" + electrium +
                ", platinium=" + platinium +
                '}';
    }
}



