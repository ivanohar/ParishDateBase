package com.ohar.pdb.controller;

import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.helper.StageHelper;
import com.ohar.pdb.model.User;
import com.ohar.pdb.model.enums.Activation;
import com.ohar.pdb.model.enums.Status;
import com.ohar.pdb.services.SearchUserService;
import com.ohar.pdb.services.ValidationService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class UserWinController {

    private SearchUserService userService = AbstractServiceFactory.getInstance().getSearchUserService();
    private ValidationService validationService = AbstractServiceFactory.getInstance().getValidationService();
    private StageHelper helper = StageHelper.getInstance();

    @FXML
    private AnchorPane userPanel;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> idColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> mailColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> statusColumn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField mailField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<String> statusField;

    @FXML
    private TextField idField;

    @FXML
    private Button createNewUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private TableColumn<User, String> activationColumn;

    @FXML
    private ComboBox<String> activationField;

    @FXML
    void cancel(ActionEvent event) {
        helper.forwardMain(null);
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ParishDateBase");
        alert.setContentText("Жадаеце выдаліць?");
        ButtonType okButton = new ButtonType("Так", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Не", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        alert.showAndWait().ifPresent(type -> {
            if (type == okButton) {
                userService.deleteUser(idField.getText().isEmpty() ? null : Long.parseLong(idField.getText()));
                cleanFields();
                updateUserTable();
            }
        });
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName()));
        mailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMail()));
        loginColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLogin()));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus() != null ? cellData.getValue().getStatus().getText() : ""));
        statusField.getItems().addAll(Status.ABBOT.getText(), Status.VICAR.getText(), Status.SECRETARY.getText());
        activationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActivation() != null ? cellData.getValue().getActivation().getText() : ""));
        activationField.getItems().addAll(Activation.YES.getText(), Activation.NO.getText(), Activation.BLOCKED.getText());
        updateUserTable();
    }

    private void updateUserTable() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        userList.addAll(userService.getAllUsers());
        userTable.setItems(userList);
    }

    @FXML
    void save(ActionEvent event) {
        User user = new User();
        user.setId(idField.getText().isEmpty() ? null : Long.parseLong(idField.getText()));
        user.setFirstName(firstNameField.getText());
        user.setLastName(lastNameField.getText());
        user.setMail(mailField.getText());
        user.setLogin(loginField.getText());
        user.setPassword(passwordField.getText());
        user.setStatus(Status.getStatusByText(statusField.getValue()));
        user.setActivation(Activation.getActivationByText(activationField.getValue()));
        if (validateUser(user)) {
            if (user.getId() == null || "".equals(user.getId())) {
                user = userService.createUser(user);
            } else {
                user = userService.updateUser(user);
            }
            updateUserTable();
            cleanFields();
        }
    }

    private void cleanFields() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        mailField.clear();
        loginField.clear();
        passwordField.clear();
        statusField.setValue(null);
        activationField.setValue(null);
    }

    public void initializeUser(User user) {
        idField.setText(user.getId().toString());
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        mailField.setText(user.getMail());
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        statusField.setValue(user.getStatus().getText());
        activationField.setValue(user.getActivation().getText());
    }

    @FXML
    void itemChooseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            User selectedItem = userTable.getSelectionModel().getSelectedItem();
            User selectedUser = userService.getUser(selectedItem.getId());
            initializeUser(selectedUser);
        }
    }

    private boolean validateUser(User user) {
        List<String> errors = validationService.validateUser(user, true);
        if (errors.size() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate User");
            alert.setHeaderText(null);
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean simpleTextValidate(String text) {
        String regex = "^[\\p{L}\\p{M}]+";
        return text.matches(regex);
    }
}

