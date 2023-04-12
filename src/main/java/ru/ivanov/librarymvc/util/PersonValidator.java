package ru.ivanov.librarymvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivanov.librarymvc.dao.PersonDAO;
import ru.ivanov.librarymvc.models.Person;
import ru.ivanov.librarymvc.services.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //check if email and phoneNumber is unique
        Optional<Person> dbPerson = peopleService.findOneByEmail(person.getEmail());
        if(dbPerson.isPresent() && dbPerson.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email is already registered");
        }
        dbPerson = peopleService.findOneByPhoneNumber(person.getPhoneNumber());
        if(dbPerson.isPresent() && dbPerson.get().getId() != person.getId()) {
            errors.rejectValue("phoneNumber", "", "This phone number is already registered");
        }
    }
}
