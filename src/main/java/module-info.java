module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    opens com.elixir to javafx.fxml;
    exports com.elixir;
}