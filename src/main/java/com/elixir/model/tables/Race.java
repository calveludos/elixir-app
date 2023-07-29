package com.elixir.model.tables;

import java.util.List;
import java.util.Map;

public class Race {
    public static class Class {
        private int id;
        private String name;
        private String dicePv;
        private double minHeight;
        private double maxHeight;
        private int minWeight;
        private int maxWeight;
        private int matureAge;
        private int lifeExpect;
        private List<Language> languages;
        private Map<String, Integer> bonus;
        private int moveBase;
        private List<Alignment> alignments;
        private Map<String, Integer> vision;
        private String abilities;

        // Construtores, getters e setters, etc.

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDicePv() {
            return dicePv;
        }

        public void setDicePv(String dicePv) {
            this.dicePv = dicePv;
        }

        public double getMinHeight() {
            return minHeight;
        }

        public void setMinHeight(double minHeight) {
            this.minHeight = minHeight;
        }

        public double getMaxHeight() {
            return maxHeight;
        }

        public void setMaxHeight(double maxHeight) {
            this.maxHeight = maxHeight;
        }

        public int getMinWeight() {
            return minWeight;
        }

        public void setMinWeight(int minWeight) {
            this.minWeight = minWeight;
        }

        public int getMaxWeight() {
            return maxWeight;
        }

        public void setMaxWeight(int maxWeight) {
            this.maxWeight = maxWeight;
        }

        public int getMatureAge() {
            return matureAge;
        }

        public void setMatureAge(int matureAge) {
            this.matureAge = matureAge;
        }

        public int getLifeExpect() {
            return lifeExpect;
        }

        public void setLifeExpect(int lifeExpect) {
            this.lifeExpect = lifeExpect;
        }

        public List<Language> getLanguages() {
            return languages;
        }

        public void setLanguages(List<Language> languages) {
            this.languages = languages;
        }

        public Map<String, Integer> getBonus() {
            return bonus;
        }

        public void setBonus(Map<String, Integer> bonus) {
            this.bonus = bonus;
        }

        public int getMoveBase() {
            return moveBase;
        }

        public void setMoveBase(int moveBase) {
            this.moveBase = moveBase;
        }

        public List<Alignment> getAlignments() {
            return alignments;
        }

        public void setAlignments(List<Alignment> alignments) {
            this.alignments = alignments;
        }

        public Map<String, Integer> getVision() {
            return vision;
        }

        public void setVision(Map<String, Integer> vision) {
            this.vision = vision;
        }

        public String getAbilities() {
            return abilities;
        }

        public void setAbilities(String abilities) {
            this.abilities = abilities;
        }
    }

    public static class Language {
        private int id;
        private String name;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Alignment {
        private int id;
        private String name;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
