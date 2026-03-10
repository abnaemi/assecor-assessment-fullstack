package com.interview.backend.repository;
import com.interview.backend.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JpaPersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByColorIgnoreCase(String color);
}