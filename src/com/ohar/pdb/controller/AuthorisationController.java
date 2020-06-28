package com.ohar.pdb.controller;

import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.helper.StageHelper;
import com.ohar.pdb.model.Person;
import com.ohar.pdb.model.User;
import com.ohar.pdb.model.enums.*;
import com.ohar.pdb.services.SearchUserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AuthorisationController {

    private SearchUserService service = AbstractServiceFactory.getInstance().getSearchUserService();
    private StageHelper helper = StageHelper.getInstance();

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authorisationButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Button authorisationButtonPush;

    @FXML
    private CheckBox isPassword;
    @FXML
    private TextField textField;
    @FXML
    private Button openUsers;
    @FXML
    void authorisationAction(ActionEvent event) {

        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            helper.createAlert(Alert.AlertType.WARNING, "ParishDateBase", "Пароль і лагін не могуць быць пустымі");
        } else {
            User user = service.authorization(loginField.getText(), passwordField.getText());
            if (user != null) {
                switch (user.getActivation()) {
                    case YES:
                        helper.forwardMain(user);
                        break;
                    case NO:
                        helper.createAlert(Alert.AlertType.WARNING, "ParishDateBase", "Чакайце на актывацыю");
                        break;
                    case BLOCKED:
                        helper.createAlert(Alert.AlertType.WARNING, "ParishDateBase", "Ваш акаўнт часова заблакаваны");
                        break;
                }

            } else {
                helper.createAlert(Alert.AlertType.WARNING, "ParishDateBase", "Карыстальніка з такім Паролем і Лагінам не існуе...");
            }
        }
    }

    @FXML
    void showPassword(ActionEvent event) {
        if (isPassword.isSelected()) {
            String password = passwordField.getText();
            textField.setVisible(true);
            textField.setText(password);
        } else {
            isPassword.isDisable();
            textField.setVisible(false);
        }
    }

    private Person createPerson() {
        Person person = new Person();
        person.setLastName("Іваноў");
        person.setFirstName("Іван");
        person.setFatherName("Іванавіч");
        person.setBirthday(LocalDate.of(2000, 8, 23));
        person.setGender(Gender.MALE);
        person.setEducation("Інжынер");
        person.setProfession("Таксіст");
        person.setCity("Мінск");
        person.setStreet("Першая");
        person.setNumberHouse("23");
        person.setNumberFlat("13");
        person.setTelephon("22-34-345");
        person.setMobile("(29)56-76-678");
        person.setHrostDate(LocalDate.of(2000, 9, 23));
        person.setKamuniyaDate(LocalDate.of(2010, 5, 12));
        person.setKanfirmazyiaDate(LocalDate.of(2016, 4, 30));
        person.setSuzhenstvaDate(LocalDate.of(2020, 3, 11));
        person.setReligionEducation(ReligionEducation.YES);
        person.setUdzelImshy(UdzelImshy.OFTEN);
        person.setSpovedz(Spovedz.OFTEN);
        person.setFamilyStatus(FamilyStatus.MARR);
        return person;
    }

    @FXML
    void initialize() {
        registrationButton.setOnAction(event -> {
            registrationButton.getScene().getWindow().hide();
            helper.forwardRegistration();
        });
    }
}



