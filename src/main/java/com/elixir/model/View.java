package com.elixir.model;

import java.io.Serializable;

public class View implements Serializable {
    private int id;
    private int id_user;
    private int id_alignment;
    private int id_attribute;
    private int id_class;
    private int id_race;
    private int id_folder;
    private int id_slots;
    private int id_Inventory;
    private int id_Currency;
    private int id_character;
    private String name;
    private String playerName;
    private long experience;
    private int height;
    private int weight;
    private int currentPv;
    private int maxPv;
    private int classArmorBonus;
    private String appearance;
    private String background;
    private String imagePath;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int gold;
    private int silver;
    private int copper;
    private int electrium;
    private int platinium;
    private int itemId;
    private int typeItemId;
    private int languageId;
    private int iLevel;
    private int iiLevel;
    private int iiiLevel;
    private int ivLevel;
    private int vLevel;
    private int viLevel;
    private int viiLevel;
    private int viiiLevel;
    private int ixLevel;

    public int level;

    public View() {
        // Construtor vazio
    }

    public View(int id, int id_user, int id_alignment, int id_attribute, int id_class, int id_race, int id_folder, int id_slots, int id_Inventory, int id_Currency, int id_character, String name, String playerName, long experience, int height, int weight, int currentPv, int maxPv, int classArmorBonus, String appearance, String background, String imagePath, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, int gold, int silver, int copper, int electrium, int platinium, int itemId, int typeItemId, int languageId, int iLevel, int iiLevel, int iiiLevel, int ivLevel, int vLevel, int viLevel, int viiLevel, int viiiLevel, int ixLevel, int level) {
        this.id = id;
        this.id_user = id_user;
        this.id_alignment = id_alignment;
        this.id_attribute = id_attribute;
        this.id_class = id_class;
        this.id_race = id_race;
        this.id_folder = id_folder;
        this.id_slots = id_slots;
        this.id_Inventory = id_Inventory;
        this.id_Currency = id_Currency;
        this.id_character = id_character;
        this.name = name;
        this.playerName = playerName;
        this.experience = experience;
        this.height = height;
        this.weight = weight;
        this.currentPv = currentPv;
        this.maxPv = maxPv;
        this.classArmorBonus = classArmorBonus;
        this.appearance = appearance;
        this.background = background;
        this.imagePath = imagePath;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
        this.electrium = electrium;
        this.platinium = platinium;
        this.itemId = itemId;
        this.typeItemId = typeItemId;
        this.languageId = languageId;
        this.iLevel = iLevel;
        this.iiLevel = iiLevel;
        this.iiiLevel = iiiLevel;
        this.ivLevel = ivLevel;
        this.vLevel = vLevel;
        this.viLevel = viLevel;
        this.viiLevel = viiLevel;
        this.viiiLevel = viiiLevel;
        this.ixLevel = ixLevel;
        this.level = level;
    }

    // Getters e Setters para todos os atributos


    public int getId_slots() {
        return id_slots;
    }

    public void setId_slots(int id_slots) {
        this.id_slots = id_slots;
    }

    public int getId_Inventory() {
        return id_Inventory;
    }

    public void setId_Inventory(int id_Inventory) {
        this.id_Inventory = id_Inventory;
    }

    public int getId_Currency() {
        return id_Currency;
    }

    public void setId_Currency(int id_Currency) {
        this.id_Currency = id_Currency;
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

    public int getId_alignment() {
        return id_alignment;
    }

    public void setId_alignment(int id_alignment) {
        this.id_alignment = id_alignment;
    }

    public int getId_attribute() {
        return id_attribute;
    }

    public void setId_attribute(int id_attribute) {
        this.id_attribute = id_attribute;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public int getId_race() {
        return id_race;
    }

    public void setId_race(int id_race) {
        this.id_race = id_race;
    }

    public int getId_folder() {
        return id_folder;
    }

    public void setId_folder(int id_folder) {
        this.id_folder = id_folder;
    }

    public int getId_character() {
        return id_character;
    }

    public void setId_character(int id_character) {
        this.id_character = id_character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCurrentPv() {
        return currentPv;
    }

    public void setCurrentPv(int currentPv) {
        this.currentPv = currentPv;
    }

    public int getMaxPv() {
        return maxPv;
    }

    public void setMaxPv(int maxPv) {
        this.maxPv = maxPv;
    }

    public int getClassArmorBonus() {
        return classArmorBonus;
    }

    public void setClassArmorBonus(int classArmorBonus) {
        this.classArmorBonus = classArmorBonus;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getCopper() {
        return copper;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    public int getElectrium() {
        return electrium;
    }

    public void setElectrium(int electrium) {
        this.electrium = electrium;
    }

    public int getPlatinium() {
        return platinium;
    }

    public void setPlatinium(int platinium) {
        this.platinium = platinium;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTypeItemId() {
        return typeItemId;
    }

    public void setTypeItemId(int typeItemId) {
        this.typeItemId = typeItemId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getiLevel() {
        return iLevel;
    }

    public void setiLevel(int iLevel) {
        this.iLevel = iLevel;
    }

    public int getIiLevel() {
        return iiLevel;
    }

    public void setIiLevel(int iiLevel) {
        this.iiLevel = iiLevel;
    }

    public int getIiiLevel() {
        return iiiLevel;
    }

    public void setIiiLevel(int iiiLevel) {
        this.iiiLevel = iiiLevel;
    }

    public int getIvLevel() {
        return ivLevel;
    }

    public void setIvLevel(int ivLevel) {
        this.ivLevel = ivLevel;
    }

    public int getvLevel() {
        return vLevel;
    }

    public void setvLevel(int vLevel) {
        this.vLevel = vLevel;
    }

    public int getViLevel() {
        return viLevel;
    }

    public void setViLevel(int viLevel) {
        this.viLevel = viLevel;
    }

    public int getViiLevel() {
        return viiLevel;
    }

    public void setViiLevel(int viiLevel) {
        this.viiLevel = viiLevel;
    }

    public int getViiiLevel() {
        return viiiLevel;
    }

    public void setViiiLevel(int viiiLevel) {
        this.viiiLevel = viiiLevel;
    }

    public int getIxLevel() {
        return ixLevel;
    }

    public void setIxLevel(int ixLevel) {
        this.ixLevel = ixLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "View{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", id_alignment=" + id_alignment +
                ", id_attribute=" + id_attribute +
                ", id_class=" + id_class +
                ", id_race=" + id_race +
                ", id_folder=" + id_folder +
                ", id_slots=" + id_slots +
                ", id_Inventory=" + id_Inventory +
                ", id_Currency=" + id_Currency +
                ", id_character=" + id_character +
                ", name='" + name + '\'' +
                ", playerName='" + playerName + '\'' +
                ", experience=" + experience +
                ", height=" + height +
                ", weight=" + weight +
                ", currentPv=" + currentPv +
                ", maxPv=" + maxPv +
                ", classArmorBonus=" + classArmorBonus +
                ", appearance='" + appearance + '\'' +
                ", background='" + background + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", constitution=" + constitution +
                ", intelligence=" + intelligence +
                ", wisdom=" + wisdom +
                ", charisma=" + charisma +
                ", gold=" + gold +
                ", silver=" + silver +
                ", copper=" + copper +
                ", electrium=" + electrium +
                ", platinium=" + platinium +
                ", itemId=" + itemId +
                ", typeItemId=" + typeItemId +
                ", languageId=" + languageId +
                ", iLevel=" + iLevel +
                ", iiLevel=" + iiLevel +
                ", iiiLevel=" + iiiLevel +
                ", ivLevel=" + ivLevel +
                ", vLevel=" + vLevel +
                ", viLevel=" + viLevel +
                ", viiLevel=" + viiLevel +
                ", viiiLevel=" + viiiLevel +
                ", ixLevel=" + ixLevel +
                ", level=" + level +
                '}';
    }


}
