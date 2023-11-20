package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.controller.abstractControllers.MenuController;
import com.teamvectora.elixirapi.manager.JsonManger;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.model.CharacterMaster;
import com.teamvectora.elixirapi.model.Currency;
import com.teamvectora.elixirapi.model.tables.TypeID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ViewCharacterPage2Controller extends MenuController {

    public TableView<EquipmentTable> equipmentTableView;
    public TableColumn<?, String> equipmentColumn;
    public TableColumn<?, Double> weightColumn;
    public ListView<String> speechListView;
    @FXML
    private Button addEquipmentButton;

    @FXML
    private Label addEquipmentLabel;

    @FXML
    private Button addLanguageButton;

    @FXML
    private Label addLanguageLabel;

    @FXML
    private TextArea appearanceField;

    @FXML
    private Button backButton;

    @FXML
    private TextField classAndRaceField;

    @FXML
    private TextField currentXPField;

    @FXML
    private ImageView emptyImage;

    @FXML
    private Spinner<Integer> levelSpinner;

    @FXML
    private TextField nameCharacterField;

    @FXML
    private VBox newEquipmentsVBox;

    @FXML
    private VBox newLanguageVBox;

    @FXML
    private Button nextPageButton;

    @FXML
    private TextField nextXPField;

    @FXML
    private ImageView oldDragonLogo;

    @FXML
    private TextField piceOfCupperField;

    @FXML
    private TextField piceOfElectroField;

    @FXML
    private TextField piceOfGoldField;

    @FXML
    private TextField piceOfPlatineField;

    @FXML
    private TextField piceOfSilverField;
    @FXML
    private  TextField skullTextField;
    @FXML
    private  TextField zombieTextField;
    @FXML
    private  TextField vampireTextField;
    @FXML
    private  TextField specterTextField;
    @FXML
    private  TextField ghostTextField;
    @FXML
    private  TextField inhumaneTextField;
    @FXML
    private  TextField cannibalTextField;
    @FXML
    private  TextField mummyTextField;
    @FXML
    private TextField skeletonTextField;
    @FXML
    private Button saveButton;
    @FXML
    private  TextField openLocksTextField;
    @FXML
    private  TextField stealthTextField;
    @FXML
    private  TextField trapTextField;
    @FXML
    private  TextField climbWallTextField;
    @FXML
    private  TextField hideShadowsTextField;
    @FXML
    private  TextField prickTextField;
    @FXML
    private  TextField hearSoundsTextField;
    @FXML
    private  TextField stabTextField;

    private ObjectSaveManager reader;
    private CharacterMaster character;
    private int nextLevelXP;

    public void addEquipamentButtonAction(ActionEvent event) {
    }

    public static class EquipmentTable{
        public final String name;
        public final double weight;

        public EquipmentTable(String name, double weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }
    }

    @FXML
    public void initialize() {
        super.addHeader();

        reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");

        setHeader();
        if (character.getInventory() != null)
            setEquipments();
        setDeathLives();
        setThiefTalents();
        if (character.getSpeech() != null)
            setLanguages();
        if (character.getCurrency() != null)
            setCurrency();
        setXP();
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
                character.level = Integer.parseInt(String.valueOf((long) jsonObject.get("Nível"))) - 1;
                nextLevelXP = Integer.parseInt(String.valueOf((long) jsonObject.get("XP")));
                break;
            } else {
                maxXp = (long) jsonObject.get("XP");
            }
        }
        if (character.level == 0){
            character.level = 20;
        }

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        levelSpinner.setValueFactory(valueFactory);
        levelSpinner.getValueFactory().setValue(character.level);
        appearanceField.setText(character.getAppearance());

    }

    private void setEquipments() {
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        ObservableList<EquipmentTable> tableObservableList = FXCollections.observableArrayList();
        if (character.getInventory() != null){

            character.getInventory().forEach(inventory -> {
                JSONObject itemObject = null;
                try {
                    switch (inventory.getTypeItemId()){
                        case TypeID.WEAPONS:
                            itemObject = (JSONObject) JsonManger.get("weapons/weapons:" + inventory.getItemId());
                        case TypeID.ARMOR:
                            itemObject = (JSONObject) JsonManger.get("armor/armors:" + inventory.getItemId());
                        case TypeID.SHIELDS:
                            itemObject = (JSONObject) JsonManger.get("shields/shields:" + inventory.getItemId());
                    }

                    assert itemObject != null;
                    String name = (String) itemObject.get("name");
                    double weight = Double.parseDouble(((String) itemObject.get("weight")).replace(" kg", ""));
                    EquipmentTable equipment = new EquipmentTable(name, weight);
                    tableObservableList.add(equipment);
                } catch (IOException | ParseException e) {
                    throw new RuntimeException(e);
                } catch (java.lang.ClassCastException ignored){}

            });
        }

        equipmentTableView.setItems(tableObservableList);
    }

    private void setDeathLives() {
        String zombieTextFieldJson = "-";
        String vampireTextFieldJson = "-";
        String ghostTextFieldJson = "-";
        String specterTextFieldJson = "-";
        String cannibalTextFieldJson = "-";
        String inhumaneTextFieldJson = "-";
        String mummyTextFieldJson = "-";
        String skeletonTextFieldJson = "-";

        if (character.getClassId() == 4) {
            int characterLevel = character.level;
            JSONArray livingDeadArray = null;
            try {
                livingDeadArray = (JSONArray) JsonManger.get("class/cleric/living-dead");
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

            if (characterLevel >= 1 && characterLevel <= 20) {
                JSONObject livingDead = (JSONObject) livingDeadArray.get(characterLevel - 1);
                zombieTextFieldJson = String.valueOf(livingDead.get("Zumbi"));
                vampireTextFieldJson = String.valueOf(livingDead.get("Vampiro"));
                ghostTextFieldJson = String.valueOf(livingDead.get("Aparição"));
                specterTextFieldJson = String.valueOf(livingDead.get("Espectro"));
                cannibalTextFieldJson = String.valueOf(livingDead.get("Carniçal"));
                inhumaneTextFieldJson = String.valueOf(livingDead.get("Inumano"));
                mummyTextFieldJson = String.valueOf(livingDead.get("Múmia"));
                skeletonTextFieldJson = String.valueOf(livingDead.get("Esqueleto"));
            }
        }

        zombieTextField.setText(zombieTextFieldJson);
        vampireTextField.setText(vampireTextFieldJson);
        ghostTextField.setText(ghostTextFieldJson);
        specterTextField.setText(specterTextFieldJson);
        cannibalTextField.setText(cannibalTextFieldJson);
        inhumaneTextField.setText(inhumaneTextFieldJson);
        mummyTextField.setText(mummyTextFieldJson);
        skeletonTextField.setText(skeletonTextFieldJson);
    }

    private void setThiefTalents() {
        String openLocksTextFieldJson = "-";
        String stealthTextFieldJson = "-";
        String trapTextFieldJson = "-";
        String climbWallTextFieldJson = "-";
        String hideShadowsTextFieldJson = "-";
        String prickTextFieldJson = "-";
        String hearSoundsTextFieldJson = "-";
        String stabTextFieldJson = "-";

        if (character.getClassId() == 3) {
            int characterLevel = character.level;
            JSONArray thiefSkillsArray = null;
            try {
                thiefSkillsArray = (JSONArray) JsonManger.get("class/thief/Thief_Skills");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            if (characterLevel >= 1 && characterLevel <= 20) {
                JSONObject thiefSkills = (JSONObject) thiefSkillsArray.get(characterLevel - 1);
                openLocksTextFieldJson = String.valueOf(thiefSkills.get("abrirFechaduras"));
                stealthTextFieldJson = String.valueOf(thiefSkills.get("Mover-se_em_silêncio"));
                trapTextFieldJson = String.valueOf(thiefSkills.get("Reconhecer_desarmar_armadilhas"));
                climbWallTextFieldJson = String.valueOf(thiefSkills.get("escalar_muros"));
                hideShadowsTextFieldJson = String.valueOf(thiefSkills.get("Esconder-se_nas-sombras"));
                prickTextFieldJson = String.valueOf(thiefSkills.get("Pungar"));
                hearSoundsTextFieldJson = (String) thiefSkills.get("escutar");
                stabTextFieldJson = String.valueOf(thiefSkills.get("apunhalar"));
            }
        }

        openLocksTextField.setText(openLocksTextFieldJson + "%");
        stealthTextField.setText(stealthTextFieldJson+ "%");
        trapTextField.setText(trapTextFieldJson+ "%");
        climbWallTextField.setText(climbWallTextFieldJson+ "%");
        hideShadowsTextField.setText(hideShadowsTextFieldJson+ "%");
        prickTextField.setText(prickTextFieldJson+ "%");
        hearSoundsTextField.setText(hearSoundsTextFieldJson);
        stabTextField.setText("x"+stabTextFieldJson);
    }

    private void setLanguages() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        character.getSpeech().forEach(speech -> {
            String name;
            try {
                name = (String) JsonManger.get("languages/languages:" + (speech.getLanguageId()) + "/name");
                observableList.add(name);
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            } catch (java.lang.IndexOutOfBoundsException ignored){}
        });

        speechListView.setItems(observableList);
    }

    private void setCurrency() {
        Currency currency = character.getCurrency();
        if (currency != null){
            piceOfCupperField.setText(String.valueOf(currency.getCopper()));
            piceOfElectroField.setText(String.valueOf(currency.getElectrium()));
            piceOfGoldField.setText(String.valueOf(currency.getGold()));
            piceOfPlatineField.setText(String.valueOf(currency.getPlatinium()));
            piceOfSilverField.setText(String.valueOf(currency.getSilver()));
        }
    }

    private void setXP() {
        currentXPField.setText(String.valueOf(character.getExperience()));
        nextXPField.setText( (nextLevelXP == 0)
                ? "MAX"
                : String.valueOf(nextLevelXP));
    }


    @FXML
    void addEquipmentButtonAction(ActionEvent event) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        try {
            popupStage.setScene(new Scene(PaneManager.loadFXML("popupEquipament")));
            popupStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void addLanguageButtonAction(ActionEvent event) {

    }

    @FXML
    void backButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage1");
    }

    @FXML
    void nextPageButtonAction(ActionEvent event) {
        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage3");
    }

    @FXML
    void saveButtonAction(ActionEvent event) {

    }

}
