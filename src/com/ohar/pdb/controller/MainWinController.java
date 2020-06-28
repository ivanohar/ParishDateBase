package com.ohar.pdb.controller;

import com.ohar.pdb.helper.StageHelper;
import com.ohar.pdb.model.User;
import com.ohar.pdb.model.enums.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainWinController {
    private StageHelper helper = StageHelper.getInstance();
    @FXML
    private MenuBar menu;
    @FXML
    private MenuItem newFileMenu;
    @FXML
    private MenuItem openMenu;
    @FXML
    private MenuItem printMenu;
    @FXML
    private MenuItem openUserWin;

    @FXML
    void openNewFamily(ActionEvent event) {
        helper.forwardFamilyWin();
    }

    @FXML
    void openNewWorkWin(ActionEvent event) {
        helper.forwardWorkWin();
    }

    @FXML
    void openFindWin(ActionEvent event) {
        helper.forwardFindWin();
    }

    @FXML
    void openUserWinAction(ActionEvent event) {
        helper.forwardUser();
    }

    public void initialize(User user) {
        if (Status.ABBOT.equals(user.getStatus())) {
            openUserWin.setVisible(true);
        }
    }
}