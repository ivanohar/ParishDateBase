package com.ohar.pdb.controller;

import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.helper.StageHelper;
import com.ohar.pdb.model.User;
import com.ohar.pdb.services.SearchPersonService;
import com.ohar.pdb.services.SearchUserService;
import com.ohar.pdb.services.ValidationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;

public class RegistrationController {
    private SearchUserService userService = AbstractServiceFactory.getInstance().getSearchUserService();
    private ValidationService validationService = AbstractServiceFactory.getInstance().getValidationService();
    private StageHelper helper = StageHelper.getInstance();

    @FXML
    private TextField mailField;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Button registrationButtonPush;

    @FXML
    private TextField registrationLoginField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private PasswordField registrationPasswordField;

    @FXML
    private PasswordField repitPasswordField;

    @FXML
    void registrationAction(ActionEvent event) {
    }


    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            helper.forwardAuthorisation();
        });
        registrationButtonPush.setOnAction(event -> {
            if (registrationPasswordField.getText().equals(repitPasswordField.getText())) {
                User user = new User();
                user.setFirstName(nameField.getText());
                user.setLastName(lastNameField.getText());
                user.setMail(mailField.getText());
                user.setLogin(registrationLoginField.getText());
                user.setPassword(registrationPasswordField.getText());

                List<String> errors = validationService.validateUser(user, false);
                if (errors.isEmpty()) {
                    User registrationUser = userService.registration(user);
                    if (registrationUser != null) {
                        helper.forwardAuthorisation();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("ParishDateBase");
                        alert.setContentText("Рэгістрацыя прайўшла паспяхова. Чакайце на падцверджанне.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("ParishDateBase");
                        alert.setContentText("Немагчыма выканаць рэгістрацыю. Сервер не адказвае. Паспрабуйце яшчэ раз.");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Validate User");
                    alert.setHeaderText(null);
                    alert.setContentText(errors.toString());
                    alert.showAndWait();
                }
            } else {
                helper.createAlert(Alert.AlertType.WARNING, "ParishDateBase", "Паролі не супадаюць...");
            }
        });
    }
}
