package ru.ivanov.librarymvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.librarymvc.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByPhoneNumber(String phoneNumber);
    Optional<Person> findByEmail(String email);
}
