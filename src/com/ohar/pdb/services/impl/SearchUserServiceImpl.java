package com.ohar.pdb.services.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ohar.pdb.helper.AbstractHelper;
import com.ohar.pdb.model.User;
import com.ohar.pdb.model.enums.Activation;
import com.ohar.pdb.model.enums.Status;
import com.ohar.pdb.services.SearchUserService;
import com.ohar.pdb.util.LocalDateAdapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchUserServiceImpl implements SearchUserService {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    public List<User> getAllUsers() {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/users", "GET", null);
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return new ArrayList<>();
    }

    @Override
    public User createUser(User user) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/users", "POST", gson.toJson(user).getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<User>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public User getUser(Long id) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/users/" + id, "GET", null);
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<User>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/users/exist/" + login, "GET", null);
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<User>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/users", "PUT", gson.toJson(user).getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<User>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/users/" + id, "DELETE", id.toString().getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<Boolean>() {
            }.getType();
            // nothing to do with return value, just call and write to console
            System.out.println(gson.fromJson(jsonString, type).toString());
        }
    }

    @Override
    public User authorization(String login, String password) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/login?login=" + login + "&password=" + password, "GET", null);
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<User>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public User registration(User user) {
        user.setActivation(Activation.NO);
        user.setStatus(Status.SECRETARY);
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/register", "POST", gson.toJson(user).getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<User>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }
}
