package com.elixir.model;

import java.util.List;

public class CharacterMaster extends Character{
    Attribute attribute;
    Currency currency;
    Folder folder;
    List<Inventory> inventory;
    Slots slots;
    List<Speech> speech;

    public CharacterMaster(int alignmentId, Attribute attribute, int classId, int raceId, Folder folder, String name, String playerName, int experience, int height, int weight, int currentPv, int maxPv, int classArmorBonus, String appearance, String background, String imagePath) {
        super(alignmentId, attribute.getId(), classId, raceId, folder.getId(), name, playerName, experience, height, weight, currentPv, maxPv, classArmorBonus, appearance, background, imagePath);
    }

    public CharacterMaster(int alignmentId, int classId, int raceId, String name, String playerName, int experience, int height, int weight, int currentPv, int maxPv, int classArmorBonus, String appearance, String background, String imagePath, Attribute attribute, Currency currency, Folder folder, List<Inventory> inventory, Slots slots, List<Speech> speech) {
        super(alignmentId, attribute.getId(), classId, raceId, folder.getId(), name, playerName, experience, height, weight, currentPv, maxPv, classArmorBonus, appearance, background, imagePath);
        this.attribute = attribute;
        this.currency = currency;
        this.folder = folder;
        this.inventory = inventory;
        this.slots = slots;
        this.speech = speech;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public Slots getSlots() {
        return slots;
    }

    public void setSlots(Slots slots) {
        this.slots = slots;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public List<Speech> getSpeech() {
        return speech;
    }

    public void setSpeech(List<Speech> speech) {
        this.speech = speech;
    }
}
