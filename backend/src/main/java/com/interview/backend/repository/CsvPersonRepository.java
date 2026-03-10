package com.interview.backend.repository;
import com.interview.backend.model.Person;
import com.interview.backend.util.ColorMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("csv")
public class CsvPersonRepository implements PersonRepository {

    private final List<Person> persons = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadData();
    }

    private void loadData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/sample-input.csv")))) {
            String line;
            long idCounter = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    String[] location = parts[2].trim().split(" ", 2);
                    String zipCode = location[0];
                    String city = location.length > 1 ? location[1] : "";

                    persons.add(new Person(
                            idCounter++,
                            parts[0].trim(),
                            parts[1].trim(),
                            zipCode,
                            city,
                            ColorMapper.mapColor(parts[3].trim())
                    ));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Lesen der CSV-Datei", e);
        }
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return persons.stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }

    @Override
    public List<Person> findByColor(String color) {
        return persons.stream()
                .filter(p -> p.color().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @Override
    public Person save(Person person) {
        throw new UnsupportedOperationException("Speichern in CSV-Quelle ist nicht erlaubt (Schreibschutz).");
    }
}