module com.teamvectora.elixirapi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires json.simple;
    requires javafx.base;
    requires javafx.graphics;
    requires jbcrypt;
    requires mail;
    requires mysql.connector.j;

    exports com.teamvectora.elixirapi;
    exports com.teamvectora.elixirapi.controller;
    exports com.teamvectora.elixirapi.controller.abstractControllers;

    opens media;
    opens com.teamvectora.elixirapi to javafx.fxml;
    opens com.teamvectora.elixirapi.controller to javafx.fxml;
    opens com.teamvectora.elixirapi.controller.abstractControllers to javafx.fxml;
}