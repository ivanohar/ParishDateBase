package com.ohar.pdb.services;

import com.ohar.pdb.model.Person;
import com.ohar.pdb.model.User;

import java.util.List;

public interface ValidationService {
    List<String> validateUser(User user, boolean withStatus);

    List<String> validatePerson(Person person);
}
