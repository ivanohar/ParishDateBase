package com.ohar.pdb.services.impl;

import com.ohar.pdb.factory.AbstractServiceFactory;
import com.ohar.pdb.model.Person;
import com.ohar.pdb.model.User;
import com.ohar.pdb.model.enums.FamilyStatus;
import com.ohar.pdb.model.enums.Gender;
import com.ohar.pdb.model.enums.Status;
import com.ohar.pdb.services.ValidationService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    private Pattern simpleText = Pattern.compile("^[\\p{L}\\p{M}]+");
    private Pattern passwordText = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15})");
    private Pattern mailText = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    private Pattern loginText = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]+");
    private Pattern addressText = Pattern.compile("^[\\p{L}\\p{M}\\p{Nd}\\p{Pc}\\s/]+");

    @Override
    public List<String> validateUser(User user, boolean withStatus) {
        ArrayList<String> errors = new ArrayList<>();
        validateFirstName(user.getFirstName(), errors);
        validateLastName(user.getLastName(), errors);
        validateLogin(user, errors);
        validatePassword(user.getPassword(), errors);
        validateMail(user.getMail(), errors);
        if (withStatus) {
            validateStatus(user.getStatus(), errors);
        }
        return errors;
    }

    private void validateStatus(Status status, ArrayList<String> errors) {
        if (status == null) {
            errors.add("Выберыце значэнне для поля Статус");
        }
    }

    private void validateFirstName(String firstName, ArrayList<String> errors) {
        validateEmptyValue(firstName, "имя").ifPresent(error -> errors.add(error));
        validateWithPattern(simpleText, firstName, "Калі ласка ўвядзіце валіднае значэнне для поля Імя... Толькі літары.").ifPresent(error -> errors.add(error));
    }

    private void validateLastName(String lastName, ArrayList<String> errors) {
        validateEmptyValue(lastName, "прозвішча").ifPresent(error -> errors.add(error));
        validateWithPattern(simpleText, lastName, "Калі ласка ўвядзіце валіднае  значэнне для поля Прозвішча... Толькі літары.").ifPresent(error -> errors.add(error));
    }

    private void validateLogin(User user, ArrayList<String> errors) {
        String login = user.getLogin();
        validateEmptyValue(login, "логін").ifPresent(error -> errors.add(error));
        validateWithPattern(loginText, login, "Калі ласка ўвядзіце валіднае  значэнне для поля Лагін... Толькі лацінскія літары і лічбы.\"").ifPresent(error -> errors.add(error));
        User userFromDB = AbstractServiceFactory.getInstance().getSearchUserService().getUserByLogin(login);
        if (userFromDB != null && (user.getId() == null || !userFromDB.getId().equals(user.getId()))) {
            errors.add("Гэты Лагін ужо выкарыстоўваецца іншым карыстальнікам праграмы. Увядзіце новы Лагін!");
        }
    }

    private void validatePassword(String password, ArrayList<String> errors) {
        validateEmptyValue(password, "пароль").ifPresent(error -> errors.add(error));
        validateWithPattern(passwordText, password, "Калі ласка ўвядзіце валіднае  значэнне для поля Пароль... Пароль павінен змяшчаць не менш за 6 знакаў, у тым ліку лічбы, вялікія і малыя літары лацінскага алфавіту.").ifPresent(error -> errors.add(error));
    }

    private void validateMail(String mail, ArrayList<String> errors) {
        validateEmptyValue(mail, "E-mail").ifPresent(error -> errors.add(error));
        validateWithPattern(mailText, mail, "Калі ласка ўвядзіце валіднае  значэнне для поля E-mail").ifPresent(error -> errors.add(error));
    }

    private Optional<String> validateWithPattern(Pattern pattern, String text, String errorMessage) {
        if (text == null) {
            return Optional.empty();
        }
        Matcher m = pattern.matcher(text);
        if (m.find() && m.group().equals(text)) {
            return Optional.empty();
        } else {
            return Optional.of(errorMessage);
        }
    }

    private Optional<String> validateEmptyValue(String text, String fieldName) {
        if (text == null || text.isEmpty()) {
            return Optional.of(String.format("Поле %s не можа быць пустым...",
                    fieldName));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<String> validatePerson(Person person) {
        ArrayList<String> errors = new ArrayList<>();
        validateLastName(person.getLastName(), errors);
        validateFirstName(person.getFirstName(), errors);
        validateFatherName(person.getFatherName(), errors);
        validateEmptyDate(person.getBirthday(), "Дата Нараджэння");
        validateGender(person.getGender(), errors);
        validateFamilyStatus(person.getFamilyStatus(), errors);
        validateEducation(person.getEducation(), errors);
        validateProfession(person.getProfession(), errors);
        validateCity(person.getCity(), errors);
        validateStreet(person.getStreet(), errors);
        validateNumberHouse(person.getNumberHouse(), errors);
        validateNumberFlat(person.getNumberFlat(), errors);
        return errors;
    }

    private void validateFatherName(String fatherName, ArrayList<String> errors) {
        validateEmptyValue(fatherName, "Прозвішча").ifPresent(error -> errors.add(error));
        validateWithPattern(simpleText, fatherName, "Калі ласка ўвядзіце валіднае Імя па бацьку...").ifPresent(error -> errors.add(error));
    }

    private Optional<String> validateEmptyDate(LocalDate date, String fieldName) {
        if (date == null) {
            return Optional.of(String.format("Поле %s не можа быць пустым...", fieldName));
        } else {
            return Optional.empty();
        }
    }

    private void validateGender(Gender gender, ArrayList<String> errors) {
        if (gender == null) {
            errors.add("Выберыце значэнне для поля Пол...");
        }
    }

    private void validateFamilyStatus(FamilyStatus familyStatus, ArrayList<String> errors) {
        if (familyStatus == null) {
            errors.add("Выберыце значэнне для поля Сямейны стан...");
        }
    }

    private void validateEducation(String education, ArrayList<String> errors) {
        validateWithPattern(simpleText, education, "Калі ласка ўвядзіце валіднае значэнне для поля Адукацыя...").ifPresent(error -> errors.add(error));
    }

    private void validateProfession(String profession, ArrayList<String> errors) {
        validateWithPattern(simpleText, profession, "Калі ласка ўвядзіце валіднае значэнне для поля Праца...").ifPresent(error -> errors.add(error));
    }

    private void validateCity(String city, ArrayList<String> errors) {
        validateEmptyValue(city, "Горад").ifPresent(error -> errors.add(error));
        validateWithPattern(addressText, city, "Калі ласка ўвядзіце валіднае значэнне для поля Горад...").ifPresent(error -> errors.add(error));
    }

    private void validateStreet(String street, ArrayList<String> errors) {
        validateEmptyValue(street, "Вуліца").ifPresent(error -> errors.add(error));
        validateWithPattern(addressText, street, "Калі ласка ўвядзіце валіднае значэнне для поля Вуліца...").ifPresent(error -> errors.add(error));
    }

    private void validateNumberHouse(String numberHouse, ArrayList<String> errors) {
        validateEmptyValue(numberHouse, "№ дома").ifPresent(error -> errors.add(error));
        validateWithPattern(addressText, numberHouse, "Калі ласка ўвядзіце валіднае значэнне для поля № дома...").ifPresent(error -> errors.add(error));
    }

    private void validateNumberFlat(String numberFlat, ArrayList<String> errors) {
        validateEmptyValue(numberFlat, "Кватэра").ifPresent(error -> errors.add(error));
        validateWithPattern(addressText, numberFlat, "Калі ласка ўвядзіце валіднае значэнне для поля Кватэра...").ifPresent(error -> errors.add(error));
    }

}
