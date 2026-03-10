package com.interview.backend.repository;
import com.interview.backend.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CsvPersonRepositoryTest {

    private CsvPersonRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CsvPersonRepository();
        repository.init();
    }

    @Test
    void findAll_ShouldReturnParsedPersons() {
        List<Person> result = repository.findAll();
        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).lastName());
        assertNotNull(result.get(0).color());
    }
}