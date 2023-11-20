package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.Attribute;

import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.tables.CharacterAttributes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewCharacterPage1Controller extends MenuController {

    public TextField addsMagicArcField;
    public TextField addsMagicDivField;

    @FXML
    private TextArea appereanceField;

    @FXML
    private TextField baFieldStr;
    @FXML
    private TextField baFieldDex;

    @FXML
    private Button backButton;

    @FXML
    private TextField caField;

    @FXML
    private TextField chaField;

    @FXML
    private TextField chargeAndMaxField;

    @FXML
    private TextField classAndRaceField;

    @FXML
    private TextField conField;

    @FXML
    private TextField currentPvField;

    @FXML
    private TextField damageAjustField;

    @FXML
    private TextField deathLiveField;

    @FXML
    private TextField dexAjustsField;

    @FXML
    private TextField dexField;

    @FXML
    private ImageView emptyImage;

    @FXML
    private TextField followersField;

    @FXML
    private TextField intField;

    @FXML
    private TextField jpField;

    @FXML
    private TextField languagesField;

    @FXML
    private TextField learnMagicField;

    @FXML
    private Spinner<Integer> levelSpinner;

    @FXML
    private TextField maxPvField;

    @FXML
    private TextField mvField;

    @FXML
    private TextField nameCharacterField;

    @FXML
    private Button nextPageButton;

    @FXML
    private ImageView oldDragonLogo;

    @FXML
    private TextField protectionAjustField;

    @FXML
    private TextField pvAndProtectionAjust;

    @FXML
    private TextField reactionField;

    @FXML
    private TextField resurrectionField;

    @FXML
    private ImageView saveEditButton;

    @FXML
    private TextField strField;

    @FXML
    private TextField thiefTalentsField;

    @FXML
    private TextField wisField;

    private CharacterMaster character;
    private Attribute attribute;
    private String protectionAjustFieldText;

    private int level;

    @FXML
    public void initialize() {
        super.addHeader();

        ObjectSaveManager reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");
        attribute = character.getAttribute();

        setHeader();
        setAttributes();
        setAjusts();
        try {
            setSubAttributes();
            setBa();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private void setHeader() {
        nameCharacterField.setText(character.getName());
        String clas = MyCharactersController.getClassId(character.getClassId());
        String race = MyCharactersController.getRaceId(character.getRaceId());
        classAndRaceField.setText(clas.toUpperCase() + " / " + race.toUpperCase());

        JSONArray levelsArray;

        try {
            levelsArray = (JSONArray) JsonManger.get("class/" + CreateCharacterBackgroundController.getClass(character.getClassId()) + "/level");
        } catch (IOException e){
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long maxXp = 0;
        for (Object json :
                levelsArray) {
            JSONObject jsonObject = (JSONObject) json;
            System.out.println("max xp " + maxXp);
            System.out.println("char xp " + character.getExperience());
            if (character.getExperience() <= maxXp){
                level = Integer.parseInt(String.valueOf((long) jsonObject.get("Nível"))) - 1;
                break;
            } else {
                maxXp = (long) jsonObject.get("XP");
            }
        }
        if (level == 0){
            level = 20;
        }

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelSpinner.setValueFactory(valueFactory);
        levelSpinner.getValueFactory().setValue(level);
        appereanceField.setText(character.getAppearance());
    }

    private void setAttributes() {
        strField.setText(String.valueOf(attribute.getStrength()));
        chaField.setText(String.valueOf(attribute.getCharisma()));
        dexField.setText(String.valueOf(attribute.getDexterity()));
        conField.setText(String.valueOf(attribute.getConstitution()));
        intField.setText(String.valueOf(attribute.getIntelligence()));
        wisField.setText(String.valueOf(attribute.getWisdom()));
    }

    private void setSubAttributes() throws IOException, ParseException {
        maxPvField.setText(String.valueOf(character.getMaxPv()));
        currentPvField.setText(String.valueOf(character.getCurrentPv()));
        System.out.println("Level" + level);
        String mv = String.valueOf((long) JsonManger.get("race/race:" + character.getRaceId() + "/moveBase" ));
        String jp = String.valueOf((long) JsonManger.get("class/" + CreateCharacterBackgroundController.getClass(character.getClassId()) + "/level:" + level + "/Jogada de Proteção"));


        mvField.setText(mv);
        jpField.setText(jp);


        AtomicInteger caFieldText = new AtomicInteger(Integer.parseInt(dexAjustsField.getText()) + 10);
        if(character.getRaceId() == 4){
            caFieldText.addAndGet(2);
        }
        if (character.getInventory() != null){
            character.getInventory().stream()
                    .filter(inventory -> inventory.getTypeItemId() == 2)
                    .forEach(inventory -> {
                        try {
                            long bonus = (long) JsonManger.get("armor/armors:" + inventory.getItemId() + "/bonus_defesa");
                            caFieldText.addAndGet((int) bonus);
                        } catch (IOException | ParseException e) {
                            throw new RuntimeException(e);
                        }
                    });
            character.getInventory().stream()
                    .filter(inventory -> inventory.getTypeItemId() == 3)
                    .forEach(inventory -> {
                        try {
                            long bonus = (long) JsonManger.get("shields/shields:" + inventory.getItemId() + "/bonus_defesa");
                            caFieldText.addAndGet((int) bonus);
                        } catch (IOException | ParseException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        caField.setText(String.valueOf(caFieldText.get()));
    }

    private void setBa() throws IOException, ParseException {
        String ba = (String) JsonManger.get("class/" + CreateCharacterBackgroundController.getClass(character.getClassId()) + "/level:" + level + "/Base de Ataque");
        String attModStr = String.valueOf((long) JsonManger.get("attributes/atributos:" + attribute.getStrength() + "/modificador"));
        String attModDex = String.valueOf((long) JsonManger.get("attributes/atributos:" + attribute.getDexterity() + "/modificador"));

        int baFieldStrText;
        int baFieldDexText;
        ba = ba.replaceAll("[+/]", "");
        if(character.getClassId() == 1 && level > 6){
            baFieldStrText = Integer.parseInt(String.valueOf(ba.charAt(0))) + Integer.parseInt(attModStr);
            baFieldDexText = Integer.parseInt(String.valueOf(ba.charAt(1))) + Integer.parseInt(attModDex);
        }else {
            baFieldStrText = Integer.parseInt(ba) + Integer.parseInt(attModStr);
            baFieldDexText = Integer.parseInt(ba) + Integer.parseInt(attModDex);
        }

        baFieldStr.setText(String.valueOf(baFieldStrText));
        baFieldDex.setText(String.valueOf(baFieldDexText));
    }

    private void setAjusts() {
        String chargeText;
        String damageAjustText;

        switch (attribute.getStrength()) {
            case 1 -> {
                chargeText = "1 KG";
                damageAjustText = "-5";
            }
            case 2, 3 -> {
                chargeText = "3 KG";
                damageAjustText = "-4";
            }
            case 4, 5 -> {
                chargeText = "5 KG";
                damageAjustText = "-3";
            }
            case 6, 7 -> {
                chargeText = "12 KG";
                damageAjustText = "-2";
            }
            case 8, 9 -> {
                chargeText = "15 KG";
                damageAjustText = "-1";
            }
            case 10, 11 -> {
                chargeText = "19 KG";
                damageAjustText = "0";
            }
            case 12, 13 -> {
                chargeText = "25 KG";
                damageAjustText = "+1";
            }
            case 14, 15 -> {
                chargeText = "33 KG";
                damageAjustText = "+2";
            }
            case 16, 17 -> {
                chargeText = "43 KG";
                damageAjustText = "+3";
            }
            case 18, 19 -> {
                chargeText = "58 KG";
                damageAjustText = "+4";
            }
            case 20, 21 -> {
                chargeText = "75 KG";
                damageAjustText = "+5";
            }
            case 22, 23 -> {
                chargeText = "100 KG";
                damageAjustText = "+6";
            }
            case 24, 25 -> {
                chargeText = "135 KG";
                damageAjustText = "+7";
            }
            case 26, 27 -> {
                chargeText = "175 KG";
                damageAjustText = "+8";
            }
            case 28, 29 -> {
                chargeText = "235 KG";
                damageAjustText = "+9";
            }
            default -> {
                chargeText = "Valor fora da faixa";
                damageAjustText = "Valor não reconhecido";
            }
        }

        chargeAndMaxField.setText(chargeText);
        damageAjustField.setText(damageAjustText);

        String dexAjustsText = switch (attribute.getDexterity()) {
            case 1 -> "-5";
            case 2, 3 -> "-4";
            case 4, 5 -> "-3";
            case 6, 7 -> "-2";
            case 8, 9 -> "-1";
            case 10, 11 -> "0";
            case 12, 13 -> "+1";
            case 14, 15 -> "+2";
            case 16, 17 -> "+3";
            case 18, 19 -> "+4";
            case 20, 21 -> "+5";
            case 22, 23 -> "+6";
            case 24, 25 -> "+7";
            case 26, 27 -> "+8";
            case 28, 29 -> "+9";
            default -> "Valor fora da faixa";
        };

        dexAjustsField.setText(dexAjustsText);

        String dexHabilits = switch (attribute.getDexterity()) {
            case 1 -> "-5 -25% -25% -25%";
            case 2, 3 -> "-4 -20% -20% -20%";
            case 4, 5 -> "-3 -15% -15% -15%";
            case 6, 7 -> "-2 -10% -10% -10%";
            case 8, 9 -> "-1 -5% -5% -5%";
            case 10, 11 -> "+0 0 0 0";
            case 12, 13 -> "+1 0 +5% 0";
            case 14, 15 -> "+2 0 +10% +5%";
            case 16, 17 -> "+3 +5% +15% +10%";
            case 18, 19 -> "+4 +10% +20% +15%";
            case 20, 21 -> "+5 +15% +25% +20%";
            case 22, 23 -> "+6 +20% +30% +25%";
            case 24, 25 -> "+7 +25% +35% +30%";
            case 26, 27 -> "+8 +30% +40% +35%";
            case 28, 29 -> "+9 +35% +45% +40%";
            default -> "Valor fora da faixa";
        };

        thiefTalentsField.setText(dexHabilits.substring(3));

        String pvAndProtectionAjustText;
        String resurrectionChanceText;

        switch (attribute.getConstitution()) {
            case 1 -> {
                pvAndProtectionAjustText = "-5";
                resurrectionChanceText = "0%";
            }
            case 2, 3 -> {
                pvAndProtectionAjustText = "-4";
                resurrectionChanceText = "0%";
            }
            case 4, 5 -> {
                pvAndProtectionAjustText = "-3";
                resurrectionChanceText = "0%";
            }
            case 6, 7 -> {
                pvAndProtectionAjustText = "-2";
                resurrectionChanceText = "1%";
            }
            case 8, 9 -> {
                pvAndProtectionAjustText = "-1";
                resurrectionChanceText = "5%";
            }
            case 10, 11 -> {
                pvAndProtectionAjustText = "0";
                resurrectionChanceText = "10%";
            }
            case 12, 13 -> {
                pvAndProtectionAjustText = "+1";
                resurrectionChanceText = "25%";
            }
            case 14, 15 -> {
                pvAndProtectionAjustText = "+2";
                resurrectionChanceText = "50%";
            }
            case 16, 17 -> {
                pvAndProtectionAjustText = "+3";
                resurrectionChanceText = "75%";
            }
            case 18, 19 -> {
                pvAndProtectionAjustText = "+4";
                resurrectionChanceText = "95%";
            }
            case 20, 21 -> {
                pvAndProtectionAjustText = "+5";
                resurrectionChanceText = "100%";
            }
            case 22, 23 -> {
                pvAndProtectionAjustText = "+6";
                resurrectionChanceText = "100%";
            }
            case 24, 25 -> {
                pvAndProtectionAjustText = "+7";
                resurrectionChanceText = "100%";
            }
            case 26, 27 -> {
                pvAndProtectionAjustText = "+8";
                resurrectionChanceText = "100%";
            }
            case 28, 29 -> {
                pvAndProtectionAjustText = "+9";
                resurrectionChanceText = "100%";
            }
            default -> {
                pvAndProtectionAjustText = "Valor fora da faixa";
                resurrectionChanceText = "Valor fora da faixa";
            }
        }
        pvAndProtectionAjust.setText(pvAndProtectionAjustText);
        resurrectionField.setText(resurrectionChanceText);

        String languagesFieldText;
        String learnMagicFieldText;
        String addsMagicFieldText;

        switch (attribute.getIntelligence()) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 -> {
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
            }
            case 14, 15 -> {
                languagesFieldText = "1";
                learnMagicFieldText = "25%";
                addsMagicFieldText = "0";
            }
            case 16, 17 -> {
                languagesFieldText = "2";
                learnMagicFieldText = "35%";
                addsMagicFieldText = "1 de 1o círculo";
            }
            case 18, 19 -> {
                languagesFieldText = "3";
                learnMagicFieldText = "45%";
                addsMagicFieldText = "2 de 1o círculo";
            }
            case 20, 21 -> {
                languagesFieldText = "4";
                learnMagicFieldText = "55%";
                addsMagicFieldText = "1 de 2o | 2 de 1o";
            }
            case 22, 23 -> {
                languagesFieldText = "5";
                learnMagicFieldText = "65%";
                addsMagicFieldText = "2 de 2o | 2 de 1o";
            }
            case 24, 25 -> {
                languagesFieldText = "6";
                learnMagicFieldText = "75%";
                addsMagicFieldText = "1 de 3o | 2 de 2o | 2 de 1o";
            }
            case 26, 27 -> {
                languagesFieldText = "7";
                learnMagicFieldText = "85%";
                addsMagicFieldText = "1 de 3o | 2 de 2o | 3 de 1o";
            }
            case 28, 29 -> {
                languagesFieldText = "8";
                learnMagicFieldText = "95%";
                addsMagicFieldText = "1 de 3o | 3 de 2o | 3 de 1o";
            }
            default -> {
                languagesFieldText = "Valor não reconhecido";
                learnMagicFieldText = "Valor não reconhecido";
                addsMagicFieldText = "Valor não reconhecido";
            }
        }

        languagesField.setText(languagesFieldText);
        learnMagicField.setText(learnMagicFieldText);
        addsMagicArcField.setText(addsMagicFieldText);

        CharacterAttributes.Wisdom wisdomTable = new CharacterAttributes.Wisdom();
        protectionAjustField.setText((String) wisdomTable.getTableWisdom(attribute.getWisdom()).get("Ajuste de proteção"));
        addsMagicArcField.setText(String.valueOf((int) wisdomTable.getTableWisdom(attribute.getWisdom()).get("Total Magias divinas adicionais")));

        String followersFieldText;
        String reactionFieldText;
        String deathLiveFieldText;

        switch (attribute.getCharisma()) {
            case 1 -> {
                followersFieldText = "0";
                reactionFieldText = "-25%";
                deathLiveFieldText = "0";
            }
            case 2, 3 -> {
                followersFieldText = "0";
                reactionFieldText = "-20%";
                deathLiveFieldText = "0";
            }
            case 4, 5 -> {
                followersFieldText = "0";
                reactionFieldText = "-15%";
                deathLiveFieldText = "0";
            }
            case 6, 7 -> {
                followersFieldText = "0";
                reactionFieldText = "-10%";
                deathLiveFieldText = "0";
            }
            case 8, 9 -> {
                followersFieldText = "0";
                reactionFieldText = "-5%";
                deathLiveFieldText = "1";
            }
            case 10, 11 -> {
                followersFieldText = "1";
                reactionFieldText = "0";
                deathLiveFieldText = "1d2";
            }
            case 12, 13 -> {
                followersFieldText = "2";
                reactionFieldText = "+5%";
                deathLiveFieldText = "1d3";
            }
            case 14, 15 -> {
                followersFieldText = "3";
                reactionFieldText = "+10%";
                deathLiveFieldText = "1d4";
            }
            case 16, 17 -> {
                followersFieldText = "4";
                reactionFieldText = "+15%";
                deathLiveFieldText = "1d6";
            }
            case 18, 19 -> {
                followersFieldText = "5";
                reactionFieldText = "+20%";
                deathLiveFieldText = "1d8";
            }
            case 20, 21 -> {
                followersFieldText = "6";
                reactionFieldText = "+25%";
                deathLiveFieldText = "2d4";
            }
            case 22, 23 -> {
                followersFieldText = "7";
                reactionFieldText = "+30%";
                deathLiveFieldText = "1d10";
            }
            case 24, 25 -> {
                followersFieldText = "8";
                reactionFieldText = "+35%";
                deathLiveFieldText = "1d12";
            }
            case 26, 27 -> {
                followersFieldText = "9";
                reactionFieldText = "+40%";
                deathLiveFieldText = "2d6";
            }
            case 28, 29 -> {
                followersFieldText = "10";
                reactionFieldText = "+45%";
                deathLiveFieldText = "1d20";
            }
            default -> {
                followersFieldText = "Valor fora da faixa";
                reactionFieldText = "Valor fora da faixa";
                deathLiveFieldText = "Valor fora da faixa";
            }
        }

        followersField.setText(followersFieldText);
        reactionField.setText(reactionFieldText);
        deathLiveField.setText(deathLiveFieldText);

    }

    @FXML
    void backButtonAction(ActionEvent event) {
        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.removeObject("character");
        saveManager.removeObject("attribute");


        PaneManager manager = new PaneManager();
        manager.openPane("myCharactersPane");
    }

    @FXML
    void nextPageButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage2");

    }

}