package com.interview.backend.repository;

import com.interview.backend.model.Person;
import com.interview.backend.model.PersonEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("db")
public class DbPersonRepository implements PersonRepository {

    private final JpaPersonRepository jpaRepository;

    public DbPersonRepository(JpaPersonRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Person> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::mapToRecord)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::mapToRecord);
    }

    @Override
    public List<Person> findByColor(String color) {
        return jpaRepository.findByColorIgnoreCase(color).stream()
                .map(this::mapToRecord)
                .collect(Collectors.toList());
    }

    @Override
    public Person save(Person person) {
        PersonEntity entity = PersonEntity.builder()
                .lastName(person.lastName())
                .firstName(person.firstName())
                .zipCode(person.zipCode())
                .city(person.city())
                .color(person.color())
                .build();

        PersonEntity savedEntity = jpaRepository.save(entity);
        return mapToRecord(savedEntity);
    }

    private Person mapToRecord(PersonEntity entity) {
        return new Person(
                entity.getId(),
                entity.getLastName(),
                entity.getFirstName(),
                entity.getZipCode(),
                entity.getCity(),
                entity.getColor()
        );
    }
}