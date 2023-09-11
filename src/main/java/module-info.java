module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    opens com.elixir to javafx.fxml;
    exports com.elixir;
    requires json.simple;
    exports com.elixir.controller;
    requires javafx.base;
    opens media;
    opens com.elixir.controller to javafx.fxml;
}