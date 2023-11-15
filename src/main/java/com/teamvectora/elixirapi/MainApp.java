package com.teamvectora.elixirapi;
import com.teamvectora.elixirapi.manager.ObjectSaveManager;
import com.teamvectora.elixirapi.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage stage;
    private static String root = "initialScreenPane";

    @Override
    public void start(Stage s) throws IOException {
        stage=s;
        setRoot(root,"Elixir Maker");
        Font.loadFont(getClass().getResourceAsStream("/fonts/Cardinal.ttf"), 16);
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
        if (args.length != 0){
            root = args[0];
        }

        ObjectSaveManager saveManager = new ObjectSaveManager();
        User user = (User) saveManager.getObject("user");

        if (user != null && user.isVerify()) {
            System.out.println(user);
            root = "startScreenPane";
        }

        launch(args);
    }
}