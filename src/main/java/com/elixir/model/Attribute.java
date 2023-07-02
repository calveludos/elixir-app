package com.elixir.model;

public class Attribute extends Model {

    private int id;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public Attribute() {
    }

    public Attribute(int strength, int wisdom, int dexterity, int intelligence, int constitution, int charisma){
        setStrength(strength);
        setWisdom(wisdom);
        setDexterity(dexterity);
        setConstitution(constitution);
        setCharisma(charisma);
        setIntelligence(intelligence);
    }

    public int getId() {
        return id;
    }

    public int getStrength() {
        return strength;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                '}';
    }
}
