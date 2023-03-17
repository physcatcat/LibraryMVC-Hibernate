package ru.ivanov.librarymvc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.ivanov.librarymvc.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

@Component
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
                rs.getInt("id"),
                rs.getString("full_name"),
                rs.getString("phone_number"),
                rs.getDate("date_of_birth").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                rs.getString("email")
        );
    }
}
