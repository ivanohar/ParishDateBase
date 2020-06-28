package com.ohar.pdb.services;

import com.ohar.pdb.model.User;
import javafx.collections.ObservableList;

import java.util.List;

public interface SearchUserService {
    List<User> getAllUsers();

    User createUser(User user);

    User getUser(Long id);

    User getUserByLogin(String login);

    User updateUser(User user);

    void deleteUser(Long id);

    User authorization(String login, String password);

    User registration(User user);

}
