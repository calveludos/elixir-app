package com.elixir.model;

import java.sql.Date;

public class Atributes extends Model {
    
    private long id_character;
    private int str;
    private int wis;
    private int dex;
    private int inte;
    private int con;
    private int cha;

    public Atributes() {
    }

    public Atributes(long id_character, int str, int wis, int dex, int inte, int con, int cha) {
        setId_character(id_character);
        setStr(str);
        setWis(wis);
        setDex(dex);
        setCon(con);
        setCha(cha);
        setInte(inte);
    }

    public long getId_character() {
        return id_character;
    }

    public int getStr() {
        return str;
    }

    public int getWis() {
        return wis;
    }

    public int getDex() {
        return dex;
    }

    public int getInte() {
        return inte;
    }

    public int getCon() {
        return con;
    }

    public int getCha() {
        return cha;
    }

    public void setId_character(long id_character) {
        this.id_character = id_character;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setInte(int inte) {
        this.inte = inte;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }
}
