module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires jbcrypt;
    opens com.elixir to javafx.fxml;
    exports com.elixir;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
}