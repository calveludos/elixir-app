package com.elixir;

import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.dao.RaceDAO;
import com.elixir.model.Attribute;
import com.elixir.model.Race;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

public class NewCharacterController {

    @FXML
    private Spinner<Integer> chaField;

    @FXML
    private TextFlow characterLabel;

    @FXML
    private Spinner<Integer> conField;

    @FXML
    private Spinner<Integer> dexField;

    @FXML
    private MenuItem dwarfMenuItem;

    @FXML
    private MenuItem elfMenuItem;

    @FXML
    private MenuItem halflingMenuItem;

    @FXML
    private MenuItem humanMenuItem;

    @FXML
    private Spinner<Integer> intField;

    @FXML
    private TextField nameCharacterField;

    @FXML
    private Menu raceMenu;

    @FXML
    private Button saveButton;

    @FXML
    private Spinner<Integer> strField;

    @FXML
    private Spinner<Integer> wisField;

    private String selectedRace;
    private String idSelectedRace;

    @FXML
    private void initialize() {
        // Adicione os listeners de evento para os itens do menu
        dwarfMenuItem.setOnAction(this::raceMenuItemClicked);
        elfMenuItem.setOnAction(this::raceMenuItemClicked);
        halflingMenuItem.setOnAction(this::raceMenuItemClicked);
        humanMenuItem.setOnAction(this::raceMenuItemClicked);
    }

    @FXML
    void saveButtonAction(ActionEvent event) {
        AttributeDAO attributeDAO = new AttributeDAO();
        RaceDAO raceDAO = new RaceDAO();
        CharacterDAO characterDAO = new CharacterDAO();

        Attribute attribute = new Attribute(
                strField.getValue(),
                dexField.getValue(),
                conField.getValue(),
                intField.getValue(),
                wisField.getValue(),
                chaField.getValue()
        );

        Race race = new Race();

    }

    private void raceMenuItemClicked(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        selectedRace = menuItem.getText();
        idSelectedRace = menuItem.getId();
        raceMenu.setText(selectedRace);
    }
}
