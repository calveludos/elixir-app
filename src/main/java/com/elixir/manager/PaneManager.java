package com.elixir.manager;

import com.elixir.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class PaneManager {
    private final Stage stage;

    public PaneManager() {
        this.stage = MainApp.getStage();
    }

    public void openPane(String caminhoFXML) {;
        try {
            Scene scene = new Scene(loadFXML(caminhoFXML));
            System.out.println("Tamanho da Tela");
            System.out.println("Widht: " + stage.getWidth());
            System.out.println("Height: " + stage.getHeight());
            stage.setWidth(stage.getWidth());
            stage.setHeight(stage.getHeight());
            System.out.println("Widht: " + stage.getWidth());
            System.out.println("Height: " + stage.getHeight());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
