package com.elixir.controller.abstractControllers;

import com.elixir.controller.objects.HeaderObject;
import com.elixir.controller.objects.SideMenuObject;
import com.elixir.manager.PaneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button createNewCharacterMenuButton;

    @FXML
    private Button myCharactersMenuButton;
    @FXML
    private Button startPaneMenuButton;

    @FXML
    private Button diceMenuButtonAction;
    @FXML
    public AnchorPane anchorRoot;

    @FXML
    public void initialize() {
        addSideMenu();
        addHeader();
    }

    protected void addSideMenu(){
        VBox sideMenu = new SideMenuObject();
        AnchorPane.setLeftAnchor(sideMenu, 0.0);
        AnchorPane.setBottomAnchor(sideMenu, 0.0);
        AnchorPane.setTopAnchor(sideMenu, 0.0);
        anchorRoot.getChildren().add(0, sideMenu);
    }

    protected void addHeader(){
        HBox header = new HeaderObject();
        AnchorPane.setLeftAnchor(header, 0.0);
        AnchorPane.setTopAnchor(header, 0.0);
        AnchorPane.setRightAnchor(header, 0.0);
        anchorRoot.getChildren().add(1, header);
    }

    @FXML
    public void createNewCharacterMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("newCharacterPane");
    }

    @FXML
    public void myCharactersMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("myCharactersPane");
    }

    @FXML
    public void startPaneMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("startScreenPane");
    }

    @FXML
    public void diceMenuButtonAction(ActionEvent event) {
        PaneManager paneManager = new PaneManager();
        paneManager.openPane("rollDicePane");
    }
}
