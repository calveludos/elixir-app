package com.elixir.model;

public class Slots {
    private int id;
    private int I_level;
    private int II_level;
    private int III_level;
    private int IV_level;
    private int V_level;
    private int VI_level;
    private int VII_level;
    private int VIII_level;
    private int IX_level;

    public Slots() {
    }

    public Slots(int i_level, int II_level, int III_level, int IV_level, int v_level, int VI_level, int VII_level, int VIII_level, int IX_level) {
        I_level = i_level;
        this.II_level = II_level;
        this.III_level = III_level;
        this.IV_level = IV_level;
        V_level = v_level;
        this.VI_level = VI_level;
        this.VII_level = VII_level;
        this.VIII_level = VIII_level;
        this.IX_level = IX_level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getI_level() {
        return I_level;
    }

    public void setI_level(int i_level) {
        I_level = i_level;
    }

    public int getII_level() {
        return II_level;
    }

    public void setII_level(int II_level) {
        this.II_level = II_level;
    }

    public int getIII_level() {
        return III_level;
    }

    public void setIII_level(int III_level) {
        this.III_level = III_level;
    }

    public int getIV_level() {
        return IV_level;
    }

    public void setIV_level(int IV_level) {
        this.IV_level = IV_level;
    }

    public int getV_level() {
        return V_level;
    }

    public void setV_level(int v_level) {
        V_level = v_level;
    }

    public int getVI_level() {
        return VI_level;
    }

    public void setVI_level(int VI_level) {
        this.VI_level = VI_level;
    }

    public int getVII_level() {
        return VII_level;
    }

    public void setVII_level(int VII_level) {
        this.VII_level = VII_level;
    }

    public int getVIII_level() {
        return VIII_level;
    }

    public void setVIII_level(int VIII_level) {
        this.VIII_level = VIII_level;
    }

    public int getIX_level() {
        return IX_level;
    }

    public void setIX_level(int IX_level) {
        this.IX_level = IX_level;
    }
}
