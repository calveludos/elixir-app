package com.elixir.model;

import java.io.Serializable;
import java.util.List;

public class CharacterMaster extends Character implements Serializable {
    Attribute attribute;
    Currency currency;
    Folder folder;
    List<Inventory> inventory;
    Slots slots;
    List<Speech> speech;
    List<Spell> spells;

    public CharacterMaster(int alignmentId, Attribute attribute, int classId, int raceId, Folder folder, String name, String playerName, int experience, int height, int weight, int currentPv, int maxPv, int classArmorBonus, String appearance, String background, String imagePath) {
        super(alignmentId, attribute.getId(), classId, raceId, folder.getId(), name, playerName, experience, height, weight, currentPv, maxPv, classArmorBonus, appearance, background, imagePath);
    }

    public CharacterMaster(Character character, Attribute attribute, Currency currency, Folder folder, List<Inventory> inventory, Slots slots, List<Speech> speech) {
        super(character.getAlignmentId(), attribute.getId(), character.getClassId(), character.getRaceId(), folder.getId(), character.getName(), character.getPlayerName(), character.getExperience(), character.getHeight(), character.getWeight(), character.getCurrentPv(), character.getMaxPv(), character.getClassArmorBonus(), character.getAppearance(), character.getBackground(), character.getImagePath());
        this.attribute = attribute;
        this.currency = currency;
        this.folder = folder;
        this.inventory = inventory;
        this.slots = slots;
        this.speech = speech;
    }

    public CharacterMaster() {
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

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public void addInventory(Inventory inventory){
        this.inventory.add(inventory);
    }

    public List<Speech> getSpeech() {
        return speech;
    }

    public void setSpeech(List<Speech> speech) {
        this.speech = speech;
    }

    public void addSpeech(Speech speech) {
        this.speech.add(speech);
    }

    public void setCharacter(Character character) {
        setId(character.getId());
        setAlignmentId(character.getAlignmentId());
        setAttributeId(character.getAttributeId());
        setClassId(character.getClassId());
        setRaceId(character.getRaceId());
        setFolderId(character.getFolderId());
        setName(character.getName());
        setPlayerName(character.getPlayerName());
        setExperience(character.getExperience());
        setHeight(character.getHeight());
        setWeight(character.getWeight());
        setCurrentPv(character.getCurrentPv());
        setMaxPv(character.getMaxPv());
        setClassArmorBonus(character.getClassArmorBonus());
        setAppearance(character.getAppearance());
        setBackground(character.getBackground());
        setImagePath(character.getImagePath());
        level = character.level;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public void addSpell(Spell spell){
        spells.add(spell);
    }
}
