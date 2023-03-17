package ru.ivanov.librarymvc.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;
    //TODO: заполнить регулярные выражения
    @NotEmpty(message = "Name couldn't be empty")
    @Pattern(regexp = "[А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+", message = "Write full name in format: Surname Name Lastname")
    @Size(min = 2, max = 80, message = "Name length couldn't be greater than 80 or less than 2 characters")
    private String name;

    @NotEmpty(message = "Phone number couldn't be empty")
    @Pattern(regexp = "\\d{10}", message = "Incorrect phone number, write 10 digits of phone number (without +7 or 8 in the beginning)")
    private String phoneNumber;

    @NotEmpty(message = "Date of birth couldn't be empty")
    private String birthDate;

    @NotEmpty(message = "Email couldn't be empty")
    @Email(message = "Incorrect email, write email in format: yourAddress@example.com")
    private String email;
}
