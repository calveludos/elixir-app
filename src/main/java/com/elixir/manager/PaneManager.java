package com.elixir.manager;

import com.elixir.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class PaneManager {
    private Stage stage;

    public PaneManager(Stage stage) {
        this.stage = stage;
    }

    public void openPane(String caminhoFXML) {
        try {
            Scene scene = new Scene(loadFXML(caminhoFXML));
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
