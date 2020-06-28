package com.ohar.pdb.services.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ohar.pdb.helper.AbstractHelper;
import com.ohar.pdb.model.Person;
import com.ohar.pdb.services.SearchPersonService;
import com.ohar.pdb.util.LocalDateAdapter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchPersonServiceImpl implements SearchPersonService {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    @Override
    public List<Person> findPerson(String search) {
        String jsonString;
        if (search.isEmpty()) {
            jsonString = AbstractHelper.runConnection("http://localhost:8080/persons", "GET", null);
        } else {
            jsonString = AbstractHelper.runConnection("http://localhost:8080/persons/search/" + AbstractHelper.encodeParam(search), "GET", null);
        }
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<List<Person>>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return new ArrayList<>();
    }

    @Override
    public Person createPerson(Person person) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/persons", "POST", gson.toJson(person).getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<Person>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public Person getPerson(Long id) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/persons/" + id, "GET", null);
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<Person>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public Person updatePerson(Person person) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/persons", "PUT", gson.toJson(person).getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<Person>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }

    @Override
    public void deletePerson(Long id) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/persons/" + id, "DELETE", id.toString().getBytes());
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<Boolean>() {
            }.getType();
            // nothing to do with return value, just call and write to console
            System.out.println(gson.fromJson(jsonString, type).toString());
        }
    }

    @Override
    public List<Person> getPersonsByIds(List<String> personIds) {
        String jsonString = AbstractHelper.runConnection("http://localhost:8080/persons/get/" + String.join(",", personIds), "GET", null);
        if (!jsonString.isEmpty()) {
            Type type = new TypeToken<List<Person>>() {
            }.getType();
            return gson.fromJson(jsonString, type);
        }
        return null;
    }
}