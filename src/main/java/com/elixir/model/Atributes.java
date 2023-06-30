package com.elixir.model;

import java.sql.Date;

public class Atributes {
    
    private long id;
    private int str;
    private int wis;
    private int dex;
    private int inte;
    private int con;
    private int cha;

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
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
