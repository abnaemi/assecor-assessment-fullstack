package com.interview.backend.repository;
import com.interview.backend.model.Person;
import java.util.List;
import java.util.Optional;


public interface PersonRepository {
    List<Person> findAll();
    Optional<Person> findById(Long id);
    List<Person> findByColor(String color);
    Person save(Person person);
}