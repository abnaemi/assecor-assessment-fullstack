package com.interview.backend.service;
import com.interview.backend.model.Person;
import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPersons();
    Optional<Person> getPersonById(Long id);
    List<Person> getPersonsByColor(String color);
    Person savePerson(Person person);
}