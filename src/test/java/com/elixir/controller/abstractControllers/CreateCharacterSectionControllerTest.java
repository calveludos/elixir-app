package com.elixir.controller.abstractControllers;

import com.elixir.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreateCharacterSectionControllerTest {

    @Test
    void addSections() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/objects/createCharacterSectionsObject.fxml"));
        System.out.println(loader);
        System.out.println((char[]) loader.load());

    }
}