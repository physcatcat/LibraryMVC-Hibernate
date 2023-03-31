package ru.ivanov.librarymvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.librarymvc.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
