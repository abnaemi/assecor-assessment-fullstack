package com.interview.backend.service;
import com.interview.backend.model.Person;
import com.interview.backend.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void getPersonById_ShouldReturnPerson() {
        Person p = new Person(1L, "Müller", "Hans", "67742", "Lauterecken", "blau");
        when(personRepository.findById(1L)).thenReturn(Optional.of(p));

        Optional<Person> result = personService.getPersonById(1L);

        assertTrue(result.isPresent());
    }
}