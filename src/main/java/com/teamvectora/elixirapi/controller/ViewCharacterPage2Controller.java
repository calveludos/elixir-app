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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ViewCharacterPage2Controller extends MenuController {

    public TableView<EquipmentTable> equipmentTableView;
    public TableColumn<?, String> equipmentColumn;
    public TableColumn<?, Double> weightColumn;
    public ListView<LanguageList> speechListView;
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

    public static class EquipmentTable{
        public final String name;
        public final double weight;
        public final int idTypeItem;
        public final int idItem;

        public EquipmentTable(String name, double weight, int idTypeItem, int idItem) {
            this.name = name;
            this.weight = weight;
            this.idTypeItem = idTypeItem;
            this.idItem = idItem;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }

        public int getIdTypeItem() {
            return idTypeItem;
        }

        public int getIdItem() {
            return idItem;
        }
    }

    public static final class LanguageList {
        public final String name;
        public final int idLanguage;

        public LanguageList(String name, int idLanguage) {
            this.name = name;
            this.idLanguage = idLanguage;
        }

        public String getName() {
            return name;
        }

        public int getIdLanguage() {
            return idLanguage;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @FXML
    public void initialize() {
        super.addHeader();

        reader = new ObjectSaveManager();
        character = (CharacterMaster) reader.getObject("character");

        setHeader();
        if (character.getInventory() != null && !character.getInventory().isEmpty())
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

            System.out.println(character.getInventory());

            character.getInventory().forEach(inventory -> {
                JSONArray arrayObject = null;
                try {
                    switch (inventory.getTypeItemId()) {
                        case TypeID.WEAPONS ->
                                arrayObject = (JSONArray) JsonManger.get("weapons/weapons");
                        case TypeID.ARMOR ->
                                arrayObject = (JSONArray) JsonManger.get("armor/armors");
                        case TypeID.SHIELDS ->
                                arrayObject = (JSONArray) JsonManger.get("shields/shields");
                    }

                    assert arrayObject != null;
                    JSONObject itemObject = (JSONObject) arrayObject.get(inventory.getItemId() - 1);
                    String name = (String) itemObject.get("name");
                    double weight = Double.parseDouble(((String) itemObject.get("weight")).replace(" kg", ""));
                    EquipmentTable equipment = new EquipmentTable(name, weight, inventory.getTypeItemId(), inventory.getItemId());
                    tableObservableList.add(equipment);
                } catch (IOException | ParseException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        equipmentTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                int selectedIndex = equipmentTableView.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("REMOVER");
                    alert.setHeaderText("Remover Equipamento");
                    alert.setContentText("Você tem certeza que deseja remover esse equipamento?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response.getText().equals("OK")){

                            System.out.println(character.getInventory());
                            character.setInventory(new ArrayList<>(character.getInventory()
                                    .stream()
                                    .filter(inventory -> inventory.getItemId() != equipmentTableView.getItems().get(selectedIndex).getIdItem() ||
                                                    inventory.getTypeItemId() != equipmentTableView.getItems().get(selectedIndex).getIdTypeItem())
                                    .toList()));
                            System.out.println(character.getInventory());


                            equipmentTableView.getItems().remove(selectedIndex);
                        }
                    });
                }
            }
        });

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
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }

            if (characterLevel >= 1 && characterLevel <= 20) {
                JSONObject thiefSkills = (JSONObject) thiefSkillsArray.get(characterLevel - 1);
                openLocksTextFieldJson = String.valueOf(thiefSkills.get("abrirFechaduras"));
                stealthTextFieldJson = String.valueOf(thiefSkills.get("Mover-se_em_silêncio"));
                trapTextFieldJson = String.valueOf(thiefSkills.get("Reconhecer_desarmar_armadilhas"));
                climbWallTextFieldJson = String.valueOf(thiefSkills.get("escalar_muros"));
                hideShadowsTextFieldJson = String.valueOf(thiefSkills.get("Esconder-se_nas_sombras"));
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
        ObservableList<LanguageList> observableList = FXCollections.observableArrayList();
        character.getSpeech().forEach(speech -> {
            try {
                String name = (String) JsonManger.get("languages/languages:" + (speech.getLanguageId()) + "/name");
                int id = Integer.parseInt(JsonManger.get("languages/languages:" + (speech.getLanguageId()) + "/id").toString());
                observableList.add(new LanguageList(name, id));
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            } catch (java.lang.IndexOutOfBoundsException ignored){}
        });

        speechListView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                int selectedIndex = speechListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("REMOVER");
                    alert.setHeaderText("Remover Idioma");
                    alert.setContentText("Você tem certeza que deseja remover esse idioma?");
                    alert.showAndWait().ifPresent(response -> {
                        if (response.getText().equals("OK")){
                            System.out.println(character.getSpeech());
                            character.setSpeech(new ArrayList<>(character.getSpeech()
                                    .stream()
                                    .filter(speech -> speech.getLanguageId() != speechListView.getItems().get(selectedIndex).getIdLanguage())
                                    .toList()));

                            System.out.println(character.getSpeech());
                            speechListView.getItems().remove(selectedIndex);
                        }
                    });
                }
            }
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

        String nextLevelXP;
        if (character.level < 20){
            try {
                nextLevelXP = JsonManger.get("class/" + CreateCharacterBackgroundController.getClass(character.getClassId()) + "/level:" + (character.level + 1) + "/XP").toString();
            } catch (IOException | ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            nextLevelXP = "MAX";
        }
        nextXPField.setText(nextLevelXP);
    }


    @FXML
    void addEquipmentButtonAction(ActionEvent event) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        try {
            popupStage.setScene(new Scene(PaneManager.loadFXML("popupEquipament")));
            popupStage.setResizable(false);
            popupStage.setTitle("EQUIPAMENTOS");
            popupStage.show();
            popupStage.setOnHidden(windowEvent -> {
                ObjectSaveManager saveManager = new ObjectSaveManager();
                character = (CharacterMaster) saveManager.getObject("character");
                if (character.getInventory() != null)
                    setEquipments();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void addLanguageButtonAction(ActionEvent event) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);

        try {
            popupStage.setScene(new Scene(PaneManager.loadFXML("popupSpeech")));
            popupStage.setResizable(false);
            popupStage.setTitle("IDIOMAS");
            popupStage.show();
            popupStage.setOnHidden(windowEvent -> {
                ObjectSaveManager saveManager = new ObjectSaveManager();
                character = (CharacterMaster) saveManager.getObject("character");
                if (character.getSpeech() != null)
                    setLanguages();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backButtonAction(ActionEvent event) {
        saveCharacter();

        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage1");
    }

    @FXML
    void nextPageButtonAction(ActionEvent event) {
        saveCharacter();

        PaneManager manager = new PaneManager();
        manager.openPane("viewCharacterPage3");
    }

    @FXML
    void saveButtonAction(ActionEvent event) {
        saveCharacter();
    }

    public void saveCharacter(){
        int idCurrency = character.getCurrency().getId();
        character.setCurrency(new Currency(
                character.getId(),
                Integer.parseInt(piceOfGoldField.getText()),
                Integer.parseInt(piceOfSilverField.getText()),
                Integer.parseInt(piceOfCupperField.getText()),
                Integer.parseInt(piceOfElectroField.getText()),
                Integer.parseInt(piceOfPlatineField.getText())
        ));
        character.getCurrency().setId(idCurrency);

        character.setName(nameCharacterField.getText());
        character.setAppearance(appearanceField.getText());
        character.level = levelSpinner.getValue();

        character.setExperience(Integer.parseInt(currentXPField.getText()));

        ObjectSaveManager saveManager = new ObjectSaveManager();
        saveManager.saveObject("character", character);
    }

}
