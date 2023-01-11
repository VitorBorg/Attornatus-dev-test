package com.vitorBorg.Attornatus.repository;

import com.vitorBorg.Attornatus.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {
    boolean existsByNamePerson(String name);
}
