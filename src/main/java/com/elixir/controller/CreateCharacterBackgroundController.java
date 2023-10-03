package com.elixir.controller;

import com.elixir.controller.abstractControllers.CreateCharacterSectionController;
import com.elixir.dao.AttributeDAO;
import com.elixir.dao.CharacterDAO;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.Attribute;
import com.elixir.model.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CreateCharacterBackgroundController extends CreateCharacterSectionController {

    @FXML
    private TextArea backgroundField;

    @FXML
    private Button createCharacterButton;

    @FXML
    private Label errorLabel;

    private Character character;
    private Attribute attribute;

    @FXML
    private void initialize(){
        ObjectSaveManager reader = new ObjectSaveManager();
        character = (Character) reader.getObject("character");
        attribute = (Attribute) reader.getObject("attribute");

        try{
            if(character.getBackground() != null){
                backgroundField.setText(character.getBackground());
            }
        } catch (NullPointerException e){
            character = new Character();
        }
    }
    @FXML
    void finishButtonAction(ActionEvent event) throws SQLException {
        finishCharacter();
    }

    private void finishCharacter() throws SQLException {
        AttributeDAO daoAttribute = new AttributeDAO();
        int attributeId = daoAttribute.create(attribute);

        character.setAttributeId(attributeId);
        character.setBackground(backgroundField.getText());

        CharacterDAO dao = new CharacterDAO();
        dao.create(character);

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane("newCharacterPane");
    }

    @Override
    protected void saveCharacter(String fxml){
        character.setBackground(backgroundField.getText());

        ObjectSaveManager saver = new ObjectSaveManager();
        saver.saveObject("character", character);

        PaneManager paneManager = new PaneManager((Stage) createCharacterButton.getScene().getWindow());
        paneManager.openPane(fxml);
    }
}
