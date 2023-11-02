package com.elixir;
import com.elixir.manager.ObjectSaveManager;
import com.elixir.manager.PaneManager;
import com.elixir.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
        System.out.println("Tamanho da Tela");
        System.out.println("Widht: " + stage.getWidth());
        System.out.println("Height: " + stage.getHeight());
        stage.setMaximized(true);
        stage.show();
        System.out.println("Widht: " + stage.getWidth());
        System.out.println("Height: " + stage.getHeight());
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