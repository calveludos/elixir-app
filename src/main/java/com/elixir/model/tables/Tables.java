package com.elixir.model.tables;

import com.elixir.CharacterViewController;

import java.util.Map;

public class Tables {
    public enum Alignment {
        LAWFUL(1, "Ordeiro"),
        CHAOTIC(3, "Caos"),
        NEUTRAL(2, "Neutro");

        private final int id;
        private final String text;

        Alignment(int id, String text) {
            this.id = id;
            this.text = text;
        }

        public int getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public static String getTextForId(int id) {
            for (Alignment alignment : values()) {
                if (alignment.getId() == id) {
                    return alignment.getText();
                }
            }
            return null; // Or throw an exception if ID is not found.
        }

        public static Alignment setAlignment(int id) {
            switch (id) {
                case 1:
                    return Alignment.LAWFUL;
                case 2:
                    return Alignment.NEUTRAL;
                case 3:
                    return Alignment.CHAOTIC;
                default:
                    throw new IllegalArgumentException("ID de alinhamento inválido: " + id);
            }
        }

    }

    public enum Race {
        HUMAN(1, "Humano", "1d10", 1.60, 1.90, 55, 90, 15, 70, "Comum e idiomas de aliados",
                "dois atributos à escolha do jogador", 9, "-", 0, "N/A"),

        DWARF(2, "Anão", "1d10", 1.30, 1.50, 50, 70, 70, 350, "Anão, Comum e idiomas de aliados e raças inimigas",
                2, 0, 6, "Ordeiro", 15, "Detectar desníveis, fossos ou armadilhas de pedra com resultado de 1 ou 2 em 1d6");

        ELF(3, "Elfo", "1d6", 1.50, 1.70, 40, 50, 150, 700, "Élfico e Comum",
                "+2 na Destreza e -2 na Constituição", 9, "Elfos possuem visão na penumbra com alcance de até 50 metros",
                "Recebem um bônus de +1 no ataque ao utilizar uma arma de arremesso",
                "Detectam portas secretas passivamente com resultado 1 em 1d6 e ativamente com 1 e 2 em 1d6",
                9),

        HALFLING(4, "Halfling", "1d10", 0.70, 0.90, 20, 35, 30, 70, "Comum e idiomas de povos amigos",
                "+2 na Destreza e -2 na Força", 6, "Neutro ou Caótico", 0, "10% de chance de passarem furtivamente ou de esconder-se",
                "+2 na classe de armadura contra alvos grandes ou maiores",
                "+1 no ataque quando utilizarem arma de arremesso",
                "Usam armas médias apenas se usarem as duas mãos e não podem utilizar armas grandes.");

        private final int id;
        private final String name;
        private final String dicePv;
        private final double minHeight;
        private final double maxHeight;
        private final int minWeight;
        private final int maxWeight;
        private final int matureAge;
        private final int lifeExpect;
        private final String languages;
        private final Map<String, Integer> bonus; //{attribute=, attack=Y class armor=Z}
        private final Map<String, Integer> penalty; //{attribute=X, attack=Y class armor=Z}
        private final int moveBase;
        private final String alignment;
        private final int vision; //{dark=X, twilight=Y}
        private final String abilities;

        Race(int id, String name, String dicePv, double minHeight, double maxHeight,
             int minWeight, int maxWeight, int matureAge, int lifeExpect, String languages,
             Map<String, Integer> bonus, Map<String, Integer> penalty, int moveBase,
             String alignment, int vision, String abilities) {
            this.id = id;
            this.name = name;
            this.dicePv = dicePv;
            this.minHeight = minHeight;
            this.maxHeight = maxHeight;
            this.minWeight = minWeight;
            this.maxWeight = maxWeight;
            this.matureAge = matureAge;
            this.lifeExpect = lifeExpect;
            this.languages = languages;
            this.bonus = bonus;
            this.penalty = penalty;
            this.moveBase = moveBase;
            this.alignment = alignment;
            this.vision = vision;
            this.abilities = abilities;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDicePv() {
            return dicePv;
        }

        public double getMinHeight() {
            return minHeight;
        }

        public double getMaxHeight() {
            return maxHeight;
        }

        public int getMinWeight() {
            return minWeight;
        }

        public int getMaxWeight() {
            return maxWeight;
        }

        public int getMatureAge() {
            return matureAge;
        }

        public int getLifeExpect() {
            return lifeExpect;
        }

        public String getLanguages() {
            return languages;
        }

        public Map<String, Integer> getBonus() {
            return bonus;
        }

        public Map<String, Integer> getPenalty() {
            return penalty;
        }

        public int getMoveBase() {
            return moveBase;
        }

        public String getAlignment() {
            return alignment;
        }

        public int getVision() {
            return vision;
        }

        public String getAbilities() {
            return abilities;
        }

        public static Race setRace(int id) {
            for (Race race : values()) {
                if (race.getId() == id) {
                    return race;
                }
            }
            throw new IllegalArgumentException("ID de raça inválido: " + id);
        }
    }


    public enum CharacterClass {
        WARRIOR(1, "Guerreiro", "1d10"),
        WIZARD(2, "Mago", "1d4"),
        ROGUE(3, "Ladrão", "1d6"),
        CLERIC(4, "Clérigo", "1d8");

        private final int id;
        private final String name;
        private final String dicePv;

        CharacterClass(int id, String name, String dicePv) {
            this.id = id;
            this.name = name;
            this.dicePv = dicePv;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDicePv() {
            return dicePv;
        }

        public static String getTextForId(int id) {
            for (CharacterClass characterClass : values()) {
                if (characterClass.getId() == id) {
                    return characterClass.getName();
                }
            }
            return null; // Or throw an exception if ID is not found.
        }

        public static CharacterClass setCharacterClass(int id) {
            switch (id) {
                case 1:
                    return CharacterClass.WARRIOR;
                case 2:
                    return CharacterClass.WIZARD;
                case 3:
                    return CharacterClass.ROGUE;
                case 4:
                    return CharacterClass.CLERIC;
                default:
                    throw new IllegalArgumentException("ID de classe inválido: " + id);
            }
        }
    }
}
