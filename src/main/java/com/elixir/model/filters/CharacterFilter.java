package com.elixir.model.filters;

public class CharacterFilter {
    private int id;
    private int raceId;
    private int attributeId;
    private int alignmentId;
    private int classId;
    private String name;
    private String playerName;
    private int idFolder;
    private int experience;
    private int level;
    private int height;
    private int weight;
    private int currentPv;
    private int maxPv;
    private int currencyId;
    private int slots;
    private String appearance;
    private int classArmorBonus;
    private String background;
    private String image;

    public String getId() {
        return (id > 0) ? " = " + id : "";
    }

    public String getRaceId() {
        return (raceId > 0) ? " = " + raceId : "";
    }

    public String getAttributeId() {
        return (attributeId > 0) ? " = " + attributeId : "";
    }

    public String getAlignmentId() {
        return (alignmentId > 0) ? " = " + alignmentId : "";
    }

    public String getClassId() {
        return (classId > 0) ? " = " + classId : "";
    }

    public String getName() {
        return (name != null && !name.isEmpty()) ? " = " + name : "";
    }

    public String getPlayerName() {
        return (playerName != null && !playerName.isEmpty()) ? " = " + playerName : "";
    }

    public String getIdFolder() {
        return (idFolder > 0) ? " = " + idFolder : "";
    }

    public String getExperience() {
        return (experience > 0) ? " = " + experience : "";
    }

    public String getLevel() {
        return (level > 0) ? " = " + level : "";
    }

    public String getHeight() {
        return (height > 0) ? " = " + height : "";
    }

    public String getWeight() {
        return (weight > 0) ? " = " + weight : "";
    }

    public String getCurrentPv() {
        return (currentPv > 0) ? " = " + currentPv : "";
    }

    public String getMaxPv() {
        return (maxPv > 0) ? " = " + maxPv : "";
    }

    public String getCurrencyId() {
        return (currencyId > 0) ? " = " + currencyId : "";
    }

    public String getSlots() {
        return (slots > 0) ? " = " + slots : "";
    }

    public String getAppearance() {
        return (appearance != null && !appearance.isEmpty()) ? " = " + appearance : "";
    }

    public String getClassArmorBonus() {
        return (classArmorBonus > 0) ? " = " + classArmorBonus : "";
    }

    public String getBackground() {
        return (background != null && !background.isEmpty()) ? " = " + background : "";
    }

    public String getImage() {
        return (image != null && !image.isEmpty()) ? " = " + image : "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public void setAlignmentId(int alignmentId) {
        this.alignmentId = alignmentId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setIdFolder(int idFolder) {
        this.idFolder = idFolder;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCurrentPv(int currentPv) {
        this.currentPv = currentPv;
    }

    public void setMaxPv(int maxPv) {
        this.maxPv = maxPv;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void setClassArmorBonus(int classArmorBonus) {
        this.classArmorBonus = classArmorBonus;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
