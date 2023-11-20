package com.teamvectora.elixirapi.controller;

import com.teamvectora.elixirapi.manager.PaneManager;
import com.teamvectora.elixirapi.manager.XMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
public class credentiaisPopupController {

    public Button cancelButton;
    public MenuItem localMenuItem;
    public MenuItem cloudMenuItem;
    public MenuButton localDatabaseMenuButton;
    @FXML
    private Button enterButton;

    @FXML
    private TextField passwordSqlField;

    @FXML
    private TextField userSqlField;

    @FXML
    private Label errorLabel;

    private XMLManager xmlManager;
    private PaneManager manager;

    @FXML
    void initialize(){
        manager = new PaneManager();
        xmlManager = new XMLManager();
        userSqlField.setText(xmlManager.getElement("user").getTextContent());
        passwordSqlField.setText(xmlManager.getElement("password").getTextContent());

        if (xmlManager.getElement("url").getTextContent().contains("elixiroffline")){
            localDatabaseMenuButton.setText(localMenuItem.getText());
        } else if (xmlManager.getElement("url").getTextContent().contains("bywkvhsiabss77ybfqwq")) {
            localDatabaseMenuButton.setText(cloudMenuItem.getText());
        }
    }

    @FXML
    void enterButtonAction(ActionEvent event) {
        String user = userSqlField.getText();
        String password = passwordSqlField.getText();
        if (user != null && !user.isEmpty() && !user.isBlank()) {
            if (password == null || password.isEmpty() || password.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmar dados");
                alert.setHeaderText("Senha vazia");
                alert.setContentText("Tem certeza que deseja cadastrar uma senha vazia?");

                alert.showAndWait();
            }
            xmlManager.editElement("user", user);
            xmlManager.editElement("password", password);
        } else {
            errorLabel.setText("Preencha os campos corretamente");
            return;
        }

        manager.openPane("initialScreenPane");
    }

    public void cancelButtonAction(ActionEvent event) {
        manager.openPane("initialScreenPane");
    }

    public void localMenuItemAction(ActionEvent event) {
        localDatabaseMenuButton.setText(localMenuItem.getText());
        xmlManager.editElement("url", "jdbc:mysql://localhost:3306/elixiroffline");
    }

    public void cloudMenuItemAction(ActionEvent event) {
        localDatabaseMenuButton.setText(cloudMenuItem.getText());
        xmlManager.editElement("url", "jdbc:mysql://mysql.services.clever-cloud.com:3306/bywkvhsiabss77ybfqwq");
        userSqlField.setText("u9eotnvveofz8mgs");
    }
}
