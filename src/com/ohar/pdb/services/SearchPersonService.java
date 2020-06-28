package com.ohar.pdb.services;

import com.ohar.pdb.model.Person;

import java.util.List;

public interface SearchPersonService {

    List<Person> findPerson(String searchString);

    Person createPerson(Person person);

    Person getPerson(Long id);

    Person updatePerson(Person person);

    void deletePerson(Long id);

    List<Person> getPersonsByIds(List<String> childrenIds);
}
