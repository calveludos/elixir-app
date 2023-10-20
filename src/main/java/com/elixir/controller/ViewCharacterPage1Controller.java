package com.elixir.controller;

import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class ViewCharacterPage1Controller {

    @FXML
    private TextField addsMagicField;

    @FXML
    private TextArea appereanceField;

    @FXML
    private TextField baField;

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
    private Spinner<?> levelSpinner;

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

    private Character character;
    private Attribute attribute;

    @FXML
    public void initialize() {
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");
        attribute = (Attribute) reader.getObject("attribute");

        setHeader();
        setAttributes();
        setSubAttributes();
        setAjusts();

    }

    private void setHeader() {
        nameCharacterField.setText(character.getName());
        String clas = MyCharactersController.getClassId(character.getClassId());
        String race = MyCharactersController.getRaceId(character.getRaceId());
        classAndRaceField.setText(clas.toUpperCase() + " / " + race.toUpperCase());
    }

    private void setAttributes() {
        strField.setText(String.valueOf(attribute.getStrength()));
        chaField.setText(String.valueOf(attribute.getCharisma()));
        dexField.setText(String.valueOf(attribute.getDexterity()));
        conField.setText(String.valueOf(attribute.getConstitution()));
        intField.setText(String.valueOf(attribute.getIntelligence()));
        wisField.setText(String.valueOf(attribute.getWisdom()));
    }

    private void setSubAttributes() {
    }

    private void setAjusts() {
        String chargeText = "";
        String damageAjustText = "";

        int strength = attribute.getStrength();

        switch (strength) {
            case 1:
                chargeText = "1 KG";
                damageAjustText = "-5";
                break;
            case 2:
            case 3:
                chargeText = "3 KG";
                damageAjustText = "-4";
                break;
            case 4:
            case 5:
                chargeText = "5 KG";
                damageAjustText = "-3";
                break;
            case 6:
            case 7:
                chargeText = "12 KG";
                damageAjustText = "-2";
                break;
            case 8:
            case 9:
                chargeText = "15 KG";
                damageAjustText = "-1";
                break;
            case 10:
            case 11:
                chargeText = "19 KG";
                damageAjustText = "0";
                break;
            case 12:
            case 13:
                chargeText = "25 KG";
                damageAjustText = "+1";
                break;
            case 14:
            case 15:
                chargeText = "33 KG";
                damageAjustText = "+2";
                break;
            case 16:
            case 17:
                chargeText = "43 KG";
                damageAjustText = "+3";
                break;
            case 18:
            case 19:
                chargeText = "58 KG";
                damageAjustText = "+4";
                break;
            case 20:
            case 21:
                chargeText = "75 KG";
                damageAjustText = "+5";
                break;
            case 22:
            case 23:
                chargeText = "100 KG";
                damageAjustText = "+6";
                break;
            case 24:
            case 25:
                chargeText = "135 KG";
                damageAjustText = "+7";
                break;
            case 26:
            case 27:
                chargeText = "175 KG";
                damageAjustText = "+8";
                break;
            case 28:
            case 29:
                chargeText = "235 KG";
                damageAjustText = "+9";
                break;
            default:
                chargeText = "Valor fora da faixa";
                damageAjustText = "Valor não reconhecido";
                break;
        }

        chargeAndMaxField.setText(chargeText);
        damageAjustField.setText(damageAjustText);


        String languagesFieldText = "";
        String learnMagicFieldText = "";
        String addsMagicFieldText = "";

        switch (attribute.getStrength()) {
            case 1:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 2:
            case 3:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 4:
            case 5:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 6:
            case 7:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 8:
            case 9:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 10:
            case 11:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 12:
            case 13:
                languagesFieldText = "0";
                learnMagicFieldText = "0%";
                addsMagicFieldText = "-";
                break;
            case 14:
            case 15:
                languagesFieldText = "1";
                learnMagicFieldText = "25%";
                addsMagicFieldText = "0";
                break;
            case 16:
            case 17:
                languagesFieldText = "2";
                learnMagicFieldText = "35%";
                addsMagicFieldText = "1 de 1o círculo";
                break;
            case 18:
            case 19:
                languagesFieldText = "3";
                learnMagicFieldText = "45%";
                addsMagicFieldText = "2 de 1o círculo";
                break;
            case 20:
            case 21:
                languagesFieldText = "4";
                learnMagicFieldText = "55%";
                addsMagicFieldText = "1 de 2o círculo e 2 de 1o círculo";
                break;
            case 22:
            case 23:
                languagesFieldText = "5";
                learnMagicFieldText = "65%";
                addsMagicFieldText = "2 de 2o círculo e 2 de 1o círculo";
                break;
            case 24:
            case 25:
                languagesFieldText = "6";
                learnMagicFieldText = "75%";
                addsMagicFieldText = "1 de 3o círculo, 2 de 2o círculo e 2 de 1o círculo";
                break;
            case 26:
            case 27:
                languagesFieldText = "7";
                learnMagicFieldText = "85%";
                addsMagicFieldText = "1 de 3o círculo, 2 de 2o círculo e 3 de 1o círculo";
                break;
            case 28:
            case 29:
                languagesFieldText = "8";
                learnMagicFieldText = "95%";
                addsMagicFieldText = "1 de 3o círculo, 3 de 2o círculo e 3 de 1o círculo";
                break;
            default:
                languagesFieldText = "Valor não reconhecido";
                learnMagicFieldText = "Valor não reconhecido";
                addsMagicFieldText = "Valor não reconhecido";
                break;
        }



        String conFieldText = "";
        String pvAndProtectionAjustText = "";
        String resurrectionChanceText = "";


        switch (attribute.getConstitution()) {
            case 1:
                pvAndProtectionAjustText = "-5 / 0%";
                resurrectionChanceText = "0%";
                break;
            case 2:
            case 3:
                pvAndProtectionAjustText = "-4 / 0%";
                resurrectionChanceText = "0%";
                break;
            case 4:
            case 5:
                pvAndProtectionAjustText = "-3 / 0%";
                resurrectionChanceText = "0%";
                break;
            case 6:
            case 7:
                pvAndProtectionAjustText = "-2 / 1%";
                resurrectionChanceText = "1%";
                break;
            case 8:
            case 9:
                pvAndProtectionAjustText = "-1 / 5%";
                resurrectionChanceText = "5%";
                break;
            case 10:
            case 11:
                pvAndProtectionAjustText = "0 / 10%";
                resurrectionChanceText = "10%";
                break;
            case 12:
            case 13:
                pvAndProtectionAjustText = "+1 / 25%";
                resurrectionChanceText = "25%";
                break;
            case 14:
            case 15:
                pvAndProtectionAjustText = "+2 / 50%";
                resurrectionChanceText = "50%";
                break;
            case 16:
            case 17:
                pvAndProtectionAjustText = "+3 / 75%";
                resurrectionChanceText = "75%";
                break;
            case 18:
            case 19:
                pvAndProtectionAjustText = "+4 / 95%";
                resurrectionChanceText = "95%";
                break;
            case 20:
            case 21:
                pvAndProtectionAjustText = "+5 / 100%";
                resurrectionChanceText = "100%";
                break;
            case 22:
            case 23:
                pvAndProtectionAjustText = "+6 / 100%";
                resurrectionChanceText = "100%";
                break;
            case 24:
            case 25:
                pvAndProtectionAjustText = "+7 / 100%";
                resurrectionChanceText = "100%";
                break;
            case 26:
            case 27:
                pvAndProtectionAjustText = "+8 / 100%";
                resurrectionChanceText = "100%";
                break;
            case 28:
            case 29:
                pvAndProtectionAjustText = "+9 / 100%";
                resurrectionChanceText = "100%";
                break;
            default:
                pvAndProtectionAjustText = "Valor fora da faixa";
                resurrectionChanceText = "Valor fora da faixa";
                break;
        }

        pvAndProtectionAjust.setText(pvAndProtectionAjustText);
        resurrectionField.setText(resurrectionChanceText);


        languagesField.setText(languagesFieldText);
        learnMagicField.setText(learnMagicFieldText);
        addsMagicField.setText(addsMagicFieldText);
        String protectionAjustFieldText = "";

        switch (attribute.getWisdom()) {
            case 1:
                protectionAjustFieldText = "-5";
                break;
            case 2:
            case 3:
                protectionAjustFieldText = "-4";
                break;
            case 4:
            case 5:
                protectionAjustFieldText = "-3";
                break;
            case 6:
            case 7:
                protectionAjustFieldText = "-2";
                break;
            case 8:
            case 9:
                protectionAjustFieldText = "-1";
                break;
            case 10:
            case 11:
                protectionAjustFieldText = "0";
                break;
            case 12:
            case 13:
                protectionAjustFieldText = "+1";
                break;
            case 14:
            case 15:
                protectionAjustFieldText = "+2";
                break;
            case 16:
            case 17:
                protectionAjustFieldText = "+3";
                break;
            case 18:
            case 19:
                protectionAjustFieldText = "+4";
                break;
            case 20:
            case 21:
                protectionAjustFieldText = "+5";
                break;
            case 22:
            case 23:
                protectionAjustFieldText = "+6";
                break;
            case 24:
            case 25:
                protectionAjustFieldText = "+7";
                break;
            case 26:
            case 27:
                protectionAjustFieldText = "+8";
                break;
            case 28:
            case 29:
                protectionAjustFieldText = "+9";
                break;
            default:
                protectionAjustFieldText = "Valor fora da faixa";
                break;
        }

        protectionAjustField.setText(protectionAjustFieldText);

        String followersFieldText = "";
        String reactionFieldText = "";
        String deathLiveFieldText = "";

        switch (attribute.getCharisma()) {
            case 1:
                followersFieldText = "0";
                reactionFieldText = "-25%";
                deathLiveFieldText = "0";
                break;
            case 2:
            case 3:
                followersFieldText = "0";
                reactionFieldText = "-20%";
                deathLiveFieldText = "0";
                break;
            case 4:
            case 5:
                followersFieldText = "0";
                reactionFieldText = "-15%";
                deathLiveFieldText = "0";
                break;
            case 6:
            case 7:
                followersFieldText = "0";
                reactionFieldText = "-10%";
                deathLiveFieldText = "0";
                break;
            case 8:
            case 9:
                followersFieldText = "0";
                reactionFieldText = "-5%";
                deathLiveFieldText = "1";
                break;
            case 10:
            case 11:
                followersFieldText = "1";
                reactionFieldText = "0";
                deathLiveFieldText = "1d2";
                break;
            case 12:
            case 13:
                followersFieldText = "2";
                reactionFieldText = "+5%";
                deathLiveFieldText = "1d3";
                break;
            case 14:
            case 15:
                followersFieldText = "3";
                reactionFieldText = "+10%";
                deathLiveFieldText = "1d4";
                break;
            case 16:
            case 17:
                followersFieldText = "4";
                reactionFieldText = "+15%";
                deathLiveFieldText = "1d6";
                break;
            case 18:
            case 19:
                followersFieldText = "5";
                reactionFieldText = "+20%";
                deathLiveFieldText = "1d8";
                break;
            case 20:
            case 21:
                followersFieldText = "6";
                reactionFieldText = "+25%";
                deathLiveFieldText = "2d4";
                break;
            case 22:
            case 23:
                followersFieldText = "7";
                reactionFieldText = "+30%";
                deathLiveFieldText = "1d10";
                break;
            case 24:
            case 25:
                followersFieldText = "8";
                reactionFieldText = "+35%";
                deathLiveFieldText = "1d12";
                break;
            case 26:
            case 27:
                followersFieldText = "9";
                reactionFieldText = "+40%";
                deathLiveFieldText = "2d6";
                break;
            case 28:
            case 29:
                followersFieldText = "10";
                reactionFieldText = "+45%";
                deathLiveFieldText = "1d20";
                break;
            default:
                followersFieldText = "Valor fora da faixa";
                reactionFieldText = "Valor fora da faixa";
                deathLiveFieldText = "Valor fora da faixa";
                break;
        }


        followersField.setText(followersFieldText);
        reactionField.setText(reactionFieldText);
        deathLiveField.setText(deathLiveFieldText);

        String thiefTalentsFieldText = "";

        switch (attribute.getDexterity()) {
            case 1:
                thiefTalentsFieldText = "-5 -25% -25% -25%";
                break;
            case 2:
            case 3:
                thiefTalentsFieldText = "-4 -20% -20% -20%";
                break;
            case 4:
            case 5:
                thiefTalentsFieldText = "-3 -15% -15% -15%";
                break;
            case 6:
            case 7:
                thiefTalentsFieldText = "-2 -10% -10% -10%";
                break;
            case 8:
            case 9:
                thiefTalentsFieldText = "-1 -5% -5% -5%";
                break;
            case 10:
            case 11:
                thiefTalentsFieldText = "0 0 0 0";
                break;
            case 12:
            case 13:
                thiefTalentsFieldText = "+1 0 +5% 0";
                break;
            case 14:
            case 15:
                thiefTalentsFieldText = "+2 0 +10% +5%";
                break;
            case 16:
            case 17:
                thiefTalentsFieldText = "+3 +5% +15% +10%";
                break;
            case 18:
            case 19:
                thiefTalentsFieldText = "+4 +10% +20% +15%";
                break;
            case 20:
            case 21:
                thiefTalentsFieldText = "+5 +15% +25% +20%";
                break;
            case 22:
            case 23:
                thiefTalentsFieldText = "+6 +20% +30% +25%";
                break;
            case 24:
            case 25:
                thiefTalentsFieldText = "+7 +25% +35% +30%";
                break;
            case 26:
            case 27:
                thiefTalentsFieldText = "+8 +30% +40% +35%";
                break;
            case 28:
            case 29:
                thiefTalentsFieldText = "+9 +35% +45% +40%";
                break;
            default:
                thiefTalentsFieldText = "Valores fora da faixa";
                break;
        }

        thiefTalentsField.setText(thiefTalentsFieldText);
    }

    @FXML
    void backButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("myCharactersPane");
    }

    @FXML
    void nextPageButtonAction(ActionEvent event) {

    }

}
