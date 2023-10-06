module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    exports com.elixir;
    opens com.elixir to javafx.fxml;
    requires json.simple;
    exports com.elixir.controller;
    requires javafx.base;
    requires javafx.graphics;
    requires jbcrypt;
    requires mysql.connector.j;
    requires commons.email;
    opens media;
    opens com.elixir.controller to javafx.fxml;
    exports com.elixir.controller.abstractControllers;
    opens com.elixir.controller.abstractControllers to javafx.fxml;
}