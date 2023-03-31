package ru.ivanov.librarymvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivanov.librarymvc.dao.PersonDAO;
import ru.ivanov.librarymvc.models.Person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //check if email and phoneNumber is unique
        Optional<Person> dbPerson = personDAO.showByEmail(person.getEmail());
        if(dbPerson.isPresent() && dbPerson.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email is already registered");
        }
        dbPerson = personDAO.showByPhoneNumber(person.getPhoneNumber());
        if(dbPerson.isPresent() && dbPerson.get().getId() != person.getId()) {
            errors.rejectValue("phoneNumber", "", "This phone number is already registered");
        }
    }
}
