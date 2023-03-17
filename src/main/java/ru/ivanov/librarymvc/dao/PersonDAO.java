package ru.ivanov.librarymvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivanov.librarymvc.models.Book;
import ru.ivanov.librarymvc.models.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, PersonMapper personMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.personMapper = personMapper;
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("update person set full_name=?, date_of_birth=?, email=?, phone_number=? where id=?",
                person.getName(),
                LocalDate.parse(person.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                person.getEmail(),
                person.getPhoneNumber(),
                id
        );
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id=?", personMapper, id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<Person> showByEmail(String email) {
        return jdbcTemplate.query("select * from person where email = ?", personMapper, email)
                .stream()
                .findAny();
    }

    public Optional<Person> showByPhoneNumber(String phoneNumber) {
        return jdbcTemplate.query("select * from person where phone_number = ?", personMapper, phoneNumber)
                .stream()
                .findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into person(full_name, date_of_birth, phone_number, email) values(?, ?, ?, ?)",
                person.getName(),
                LocalDate.parse(person.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                person.getPhoneNumber(),
                person.getEmail()
        );
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", personMapper);
    }

    public List<Book> getBooksByPerson(int personId) {
        return jdbcTemplate.query("select * from book where person_id = ?", new BeanPropertyRowMapper<>(Book.class), personId);
    }
}
