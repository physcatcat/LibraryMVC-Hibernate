package ru.ivanov.librarymvc.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.librarymvc.models.Book;
import ru.ivanov.librarymvc.models.Person;
import ru.ivanov.librarymvc.repositories.PeopleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Page<Person> findAll(Integer page, Integer limit, String sortBy) {
        return peopleRepository.findAll(PageRequest.of(page, limit, Sort.by(sortBy)));
    }

    public List<Person> findAll() {
        return peopleRepository.findAll(Sort.by("name"));
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void save(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findOneByPhoneNumber(String phoneNumber) {
        return peopleRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Person> findOneByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

    public List<Book> getBooks(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        if(person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        }
        return Collections.emptyList();
    }
}
