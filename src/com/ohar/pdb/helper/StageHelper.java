package com.ohar.pdb.helper;

import com.ohar.pdb.controller.AuthorisationController;
import com.ohar.pdb.controller.FindWinController;
import com.ohar.pdb.controller.MainWinController;
import com.ohar.pdb.controller.WorkWinController;
import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.helper.exception.CanNotCreateWindowException;
import com.ohar.pdb.model.Person;
import com.ohar.pdb.model.User;
import com.ohar.pdb.model.enums.Activation;
import com.ohar.pdb.services.SearchUserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHelper {
    private Stage stage;
    private static final StageHelper instance = new StageHelper();
    private SearchUserService userService = AbstractServiceFactory.getInstance().getSearchUserService();
    private User user;

    private StageHelper() {
    }

    public static StageHelper getInstance() {
        return instance;
    }

    public void initializeStage(Stage stage) {
        this.stage = stage;
    }

    public void forwardAuthorisation() {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.authorisation;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        stage.setTitle(windowConfig.getTitle());
        Parent load = null;
        try {
            load = loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }
        stage.setScene(new Scene(load, windowConfig.getWidth(), windowConfig.getHeight()));
        stage.show();
    }

    public void forwardSearch() {
        forwardSearch(null, null);
    }

    public void forwardSearch(WorkWinController parentController, Long personId) {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.findWin;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }

        Parent root = loader.getRoot();
        if (personId == null) {
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FindWinController controller = loader.getController();
            controller.initializePerson(parentController, personId);
            Stage childStage = new Stage();
            childStage.setScene(new Scene(root));
            childStage.showAndWait();
        }
    }

    public void forwardWorkWin() {
        Parent root = initializeWorkWin().getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void forwardWorkWin(Long relationId) {
        FXMLLoader loader = initializeWorkWin();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        if (relationId != null) {
            WorkWinController controller = loader.getController();
            controller.initializeController(null, relationId);
        }
        stage.show();
    }

    public void forwardWorkWin(Person person) {
        FXMLLoader loader = initializeWorkWin();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        if (person != null) {
            WorkWinController controller = loader.getController();
            controller.initializePerson(person);
        }
        stage.show();
    }

    public void forwardWorkWin(WorkWinController rootController, FindWinController parentController, Long relationId) {
        FXMLLoader loader = initializeWorkWin();
        Parent root = loader.getRoot();
        Stage parentStage = (Stage) parentController.getFindField().getScene().getWindow();
        parentStage.setScene(new Scene(root));
        WorkWinController controller = loader.getController();
        controller.initializeController(rootController, relationId);
        parentStage.show();
    }

    private FXMLLoader initializeWorkWin() {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.workWin;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }

        return loader;
    }

    public void forwardRegistration() {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.registration;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }

        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void forwardUser() {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.userWin;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }

        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void forwardMain(User user) {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.mainWin;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }
        if (user != null) {
            this.user = user;
        }
        MainWinController controller = loader.getController();
        controller.initialize(this.user);
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void forwardFindWin() {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.findWin;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void forwardFamilyWin() {
        FXMLLoader loader = new FXMLLoader();
        WindowConfig windowConfig = WindowConfig.familyWin;
        loader.setLocation(getClass().getResource(windowConfig.getXmlUi()));
        try {
            loader.load();
        } catch (IOException e) {
            throw new CanNotCreateWindowException(e, windowConfig);
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void createAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
