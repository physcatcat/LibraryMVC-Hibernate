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

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id=?", id);
    }

/*    public void update(int id, Person person) {
        jdbcTemplate.update("update person set full_name=?, date_of_birth=?, email=?, phone_number=? where id=?",
                person.getName(),
                person.getBirthDate(),
                person.getEmail(),
                person.getPhoneNumber(),
                id
        );
    }*/

    public Person show(int id) {
        return jdbcTemplate.query("select * from person where id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<Person> showByEmail(String email) {
        return jdbcTemplate.query("select * from person where email = ?", new BeanPropertyRowMapper<>(Person.class), email)
                .stream()
                .findAny();
    }

    public Optional<Person> showByPhoneNumber(String phoneNumber) {
        return jdbcTemplate.query("select * from person where phone_number = ?", new BeanPropertyRowMapper<>(Person.class), phoneNumber)
                .stream()
                .findAny();
    }

/*    public void save(Person person) {
        jdbcTemplate.update("insert into person(full_name, date_of_birth, phone_number, email) values(?, ?, ?, ?)",
                person.getName(),
                person.getBirthDate(),
                person.getPhoneNumber(),
                person.getEmail()
        );
    }*/

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public List<Book> getBooksByPerson(int personId) {
        return jdbcTemplate.query("select * from book where person_id = ?", new BeanPropertyRowMapper<>(Book.class), personId);
    }
}
