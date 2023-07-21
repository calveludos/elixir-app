package com.elixir;

import javafx.event.ActionEvent;

public abstract class CreateCharacterController implements CharacterCreation{

    @Override
    public void backgroundCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterBackgroundPane");
    }

    @Override
    public void classCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterClassPane");
    }

    @Override
    public void createCharacterButtonAction(ActionEvent event) {
        saveCharacter("newCharacterPane");
    }

    @Override
    public void dateCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterDatePane");
    }

    @Override
    public void raceCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterRacePane");
    }

    @Override
    public void attributesCharacterButtonAction(ActionEvent event) {
        saveCharacter("createCharacterAttributesPane");
    }

    public void saveCharacter(String fxml){}
}
