package com.elixir;

import com.elixir.manager.ObjectSaveManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage stage;
    private static String root = "initialScreenPane";

    @Override
    public void start(Stage s) throws IOException {
        stage=s;
        setRoot(root,"Elixir Maker");
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Parent root = loadFXML(fxml);
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    static void setStage(Stage stage, String title) throws IOException {

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        ObjectSaveManager saver = new ObjectSaveManager();
        saver.cleanObjects();

        if (args.length != 0){
            root = args[0];
        }

        launch(args);
    }
}
