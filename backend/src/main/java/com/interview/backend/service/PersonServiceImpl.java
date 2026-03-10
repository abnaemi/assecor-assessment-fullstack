package com.interview.backend.service;
import com.interview.backend.model.Person;
import com.interview.backend.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> getPersonsByColor(String color) {
        return personRepository.findByColor(color);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}