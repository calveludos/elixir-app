package com.elixir.model.tables;

import java.util.HashMap;
import java.util.Map;

public class CharacterAttributes {
    public static class Charism {
        private Map<String, Map<String, Object>> table;

        public Charism() {
            table = new HashMap<>();

            table.put("1", createRow(0, "-25%", 0));
            table.put("2", createRow(0, "-20%", 0));
            table.put("3", createRow(0, "-20%", 0));
            table.put("4", createRow(0, "-15%", 0));
            table.put("5", createRow(0, "-15%", 0));
            table.put("6", createRow(0, "-10%", 0));
            table.put("7", createRow(0, "-10%", 0));
            table.put("8", createRow(0, "-5%", 1));
            table.put("9", createRow(0, "-5%", 1));
            table.put("10", createRow(1, "0", "1d2"));
            table.put("11", createRow(1, "0", "1d2"));
            table.put("12", createRow(2, "+5%", "1d3"));
            table.put("13", createRow(2, "+5%", "1d3"));
            table.put("14", createRow(3, "+10%", "1d4"));
            table.put("15", createRow(3, "+10%", "1d4"));
            table.put("16", createRow(4, "+15%", "1d6"));
            table.put("17", createRow(4, "+15%", "1d6"));
            table.put("18", createRow(5, "+20%", "1d8"));
            table.put("19", createRow(5, "+20%", "1d8"));
            table.put("20", createRow(6, "+25%", "2d4"));
            table.put("21", createRow(6, "+25%", "2d4"));
            table.put("22", createRow(7, "+30%", "1d10"));
            table.put("23", createRow(7, "+30%", "1d10"));
            table.put("24", createRow(8, "+35%", "1d12"));
            table.put("25", createRow(8, "+35%", "1d12"));
            table.put("26", createRow(9, "+40%", "2d6"));
            table.put("27", createRow(9, "+40%", "2d6"));
            table.put("28", createRow(10, "+45%", "1d20"));
            table.put("29", createRow(10, "+45%", "1d20"));
        }

        private Map<String, Object> createRow(int maxFollowers, String reactionAdjustment, Object undeadTurned) {
            Map<String, Object> row = new HashMap<>();
            row.put("Número máximo de seguidores", maxFollowers);
            row.put("Ajuste de reação", reactionAdjustment);
            row.put("Mortos-vivos afastados", undeadTurned);
            return row;
        }

        public Map<String, Map<String, Object>> getTable() {
            return table;
        }
    }

    public static class Strength {
        private Map<Integer, Integer> strength = new HashMap<>();

        public Strength() {
            int ajusteAtaqueDano = -5;

            for (int i = 1; i < 30; i++) {
                strength.put(i, ajusteAtaqueDano);
                if (i % 2 != 0) {
                    ajusteAtaqueDano += 1;
                }
            }
        }

        public Map<Integer, Integer> getStrength() {
            return strength;
        }
    }

    public static class Intelligence {
        private Map<Integer, Map<String, Object>> tableIntelligence;

        public Intelligence() {
            tableIntelligence = new HashMap<>();

            int countIdiomas = 0;
            int countChance = 0;

            for (int i = 0; i < 30; i++) {
                Map<String, Object> columnIntelligence = new HashMap<>();
                Map<String, Integer> magiasAdicionais = new HashMap<>();

                if (i == 14 || i == 15) {
                    countIdiomas = 1;
                    countChance = 25;
                }

                if (i > 15 && i % 2 == 0) {
                    countIdiomas++;
                    if (countChance == 0) {
                        countChance += 25;
                    } else if (countChance >= 25 && i > 14) {
                        countChance += 10;
                    }
                }

                if (i == 14 || i == 15) {
                    magiasAdicionais.put("1° Circulo", 0);
                    magiasAdicionais.put("2° Circulo", 0);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 16 || i == 17) {
                    magiasAdicionais.put("1° Circulo", 1);
                    magiasAdicionais.put("2° Circulo", 0);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 18 || i == 19) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 1);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 20 || i == 21) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 1);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 22 || i == 23) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 24 || i == 25) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 1);
                } else if (i == 26 || i == 27) {
                    magiasAdicionais.put("1° Circulo", 3);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 1);
                } else if (i == 28 || i == 29) {
                    magiasAdicionais.put("1° Circulo", 3);
                    magiasAdicionais.put("2° Circulo", 3);
                    magiasAdicionais.put("3° Circulo", 1);
                } else {
                    magiasAdicionais.put("1° Circulo", 0);
                    magiasAdicionais.put("2° Circulo", 0);
                    magiasAdicionais.put("3° Circulo", 0);
                }

                columnIntelligence.put("Idioma", countIdiomas);
                columnIntelligence.put("Chance de aprender magias", countChance);
                columnIntelligence.put("Magias adicionais", magiasAdicionais);

                tableIntelligence.put(i, columnIntelligence);
            }
        }

        public Map<Integer, Map<String, Object>> getTableIntelligence() {
            return tableIntelligence;
        }
    }

    public static class Wisdom {
        private Map<Integer, Map<String, Object>> tableWisdom;

        public Wisdom() {
            tableWisdom = new HashMap<>();

            int countIdiomas = 0;
            int countChance = 0;
            for (int i = 0; i < 30; i++) {
                Map<String, Object> collumnWisdom = new HashMap<>();
                Map<String, Integer> magiasAdicionais = new HashMap<>();

                if (i > 13 && i % 2 != 0) {
                    countIdiomas++;
                    if (countChance == 0) {
                        countChance += 25;
                    } else if (countChance >= 25 && i > 14) {
                        countChance += 10;
                    }
                }

                if (i == 14 || i == 15) {
                    magiasAdicionais.put("1° Circulo", 0);
                    magiasAdicionais.put("2° Circulo", 0);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 16 || i == 17) {
                    magiasAdicionais.put("1° Circulo", 1);
                    magiasAdicionais.put("2° Circulo", 0);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 18 || i == 19) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 1);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 20 || i == 21) {
                    magiasAdicionais.put("1° Circulo", 1);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 22 || i == 23) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 0);
                } else if (i == 24 || i == 25) {
                    magiasAdicionais.put("1° Circulo", 2);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 1);
                } else if (i == 26 || i == 27) {
                    magiasAdicionais.put("1° Circulo", 3);
                    magiasAdicionais.put("2° Circulo", 2);
                    magiasAdicionais.put("3° Circulo", 1);
                } else if (i == 28 || i == 29) {
                    magiasAdicionais.put("1° Circulo", 3);
                    magiasAdicionais.put("2° Circulo", 3);
                    magiasAdicionais.put("3° Circulo", 1);
                } else {
                    magiasAdicionais.put("1° Circulo", 0);
                    magiasAdicionais.put("2° Circulo", 0);
                    magiasAdicionais.put("3° Circulo", 0);
                }

                collumnWisdom.put("Ajuste de proteção", getAjusteProtecao(i));
                collumnWisdom.put("Magias divinas adicionais", magiasAdicionais);

                tableWisdom.put(i, collumnWisdom);
            }
        }

        private String getAjusteProtecao(int value) {
            switch (value) {
                case 1:
                    return "-5";
                case 2:
                case 3:
                    return "-4";
                case 4:
                case 5:
                    return "-3";
                case 6:
                case 7:
                    return "-2";
                case 8:
                case 9:
                    return "-1";
                case 10:
                case 11:
                    return "0";
                case 12:
                case 13:
                    return "+1";
                case 14:
                case 15:
                    return "+2";
                case 16:
                case 17:
                    return "+3";
                case 18:
                case 19:
                    return "+4";
                case 20:
                case 21:
                    return "+5";
                case 22:
                case 23:
                    return "+6";
                case 24:
                case 25:
                    return "+7";
                case 26:
                case 27:
                    return "+8";
                case 28:
                case 29:
                    return "+9";
                default:
                    return "-";
            }
        }

        public Map<Integer, Map<String, Object>> getTableWisdom() {
            return tableWisdom;
        }
    }

    public static class Constitution {
        private Map<String, Map<String, Object>> table;

        public Constitution() {
            table = new HashMap<>();

            table.put("1", createRow(0, "-25%", 0));
            table.put("2", createRow(0, "-20%", 0));
            table.put("3", createRow(0, "-20%", 0));
            table.put("4", createRow(0, "-15%", 0));
            table.put("5", createRow(0, "-15%", 0));
            table.put("6", createRow(0, "-10%", 0));
            table.put("7", createRow(0, "-10%", 0));
            table.put("8", createRow(0, "-5%", 1));
            table.put("9", createRow(0, "-5%", 1));
            table.put("10", createRow(1, "0", "1d2"));
            table.put("11", createRow(1, "0", "1d2"));
            table.put("12", createRow(2, "+5%", "1d3"));
            table.put("13", createRow(2, "+5%", "1d3"));
            table.put("14", createRow(3, "+10%", "1d4"));
            table.put("15", createRow(3, "+10%", "1d4"));
            table.put("16", createRow(4, "+15%", "1d6"));
            table.put("17", createRow(4, "+15%", "1d6"));
            table.put("18", createRow(5, "+20%", "1d8"));
            table.put("19", createRow(5, "+20%", "1d8"));
            table.put("20", createRow(6, "+25%", "2d4"));
            table.put("21", createRow(6, "+25%", "2d4"));
            table.put("22", createRow(7, "+30%", "1d10"));
            table.put("23", createRow(7, "+30%", "1d10"));
            table.put("24", createRow(8, "+35%", "1d12"));
            table.put("25", createRow(8, "+35%", "1d12"));
            table.put("26", createRow(9, "+40%", "2d6"));
            table.put("27", createRow(9, "+40%", "2d6"));
            table.put("28", createRow(10, "+45%", "1d20"));
            table.put("29", createRow(10, "+45%", "1d20"));
        }

        private Map<String, Object> createRow(int maxFollowers, String reactionAdjustment, Object undeadTurned) {
            Map<String, Object> row = new HashMap<>();
            row.put("Número máximo de seguidores", maxFollowers);
            row.put("Ajuste de reação", reactionAdjustment);
            row.put("Mortos-vivos afastados", undeadTurned);
            return row;
        }

        public Map<String, Map<String, Object>> getTable() {
            return table;
        }
    }

    public static class Dexterity {
        private Map<Integer, Map<String, Object>> table;

        public Dexterity() {
            table = new HashMap<>();

            table.put(1, createRow("-5", "-25%", "-25%", "-25%"));
            table.put(2, createRow("-4", "-20%", "-20%", "-20%"));
            table.put(3, createRow("-4", "-20%", "-20%", "-20%"));
            table.put(4, createRow("-3", "-15%", "-15%", "-15%"));
            table.put(5, createRow("-3", "-15%", "-15%", "-15%"));
            table.put(6, createRow("-2", "-10%", "-10%", "-10%"));
            table.put(7, createRow("-2", "-10%", "-10%", "-10%"));
            table.put(8, createRow("-1", "-5%", "-5%", "-5%"));
            table.put(9, createRow("-1", "-5%", "-5%", "-5%"));
            table.put(10, createRow("0", "0", "0", "0"));
            table.put(11, createRow("0", "0", "0", "0"));
            table.put(12, createRow("+1", "0", "0", "+5%"));
            table.put(13, createRow("+1", "0", "0", "+5%"));
            table.put(14, createRow("+2", "+5%", "0", "+10%"));
            table.put(15, createRow("+2", "+5%", "0", "+10%"));
            table.put(16, createRow("+3", "+10%", "+5%", "+15%"));
            table.put(17, createRow("+3", "+10%", "+5%", "+15%"));
            table.put(18, createRow("+4", "+15%", "+10%", "+20%"));
            table.put(19, createRow("+4", "+15%", "+10%", "+20%"));
            table.put(20, createRow("+5", "+20%", "+15%", "+25%"));
            table.put(21, createRow("+5", "+20%", "+15%", "+25%"));
            table.put(22, createRow("+6", "+25%", "+20%", "+30%"));
            table.put(23, createRow("+6", "+25%", "+20%", "+30%"));
            table.put(24, createRow("+7", "+30%", "+25%", "+35%"));
            table.put(25, createRow("+7", "+30%", "+25%", "+35%"));
            table.put(26, createRow("+8", "+35%", "+30%", "+40%"));
            table.put(27, createRow("+8", "+35%", "+30%", "+40%"));
            table.put(28, createRow("+9", "+40%", "+35%", "+45%"));
            table.put(29, createRow("+9", "+40%", "+35%", "+45%"));
        }

        private Map<String, Object> createRow(String modifier, String hideInDark, String findDisarmTraps, String moveOpenLocks) {
            Map<String, Object> row = new HashMap<>();

            row.put("Ajuste de ataque e surpresa", modifier);
            row.put("Esconder-se nas sombras", hideInDark);
            row.put("Localizar e desarmar", findDisarmTraps);
            row.put("Mover-se e abrir fechaduras", moveOpenLocks);
            return row;
        }

        public Map<Integer, Map<String, Object>> getTable() {
            return table;
        }
    }

    public static void main(String[] args) {
        Charism charism = new Charism();
        System.out.println("Charism Table:");
        System.out.println(charism.getTable());

        Strength strength = new Strength();
        System.out.println("\nStrength Table:");
        System.out.println(strength.getStrength());

        Intelligence intelligence = new Intelligence();
        System.out.println("\nIntelligence Table:");
        System.out.println(intelligence.getTableIntelligence());

        Wisdom wisdom = new Wisdom();
        System.out.println("\nWisdom Table:");
        System.out.println(wisdom.getTableWisdom());

        Constitution constitution = new Constitution();
        System.out.println("\nConstitution Table:");
        System.out.println(constitution.getTable());

        Dexterity dexterity = new Dexterity();
        System.out.println("\nDexterity Table:");
        System.out.println(dexterity.getTable());
    }
}
