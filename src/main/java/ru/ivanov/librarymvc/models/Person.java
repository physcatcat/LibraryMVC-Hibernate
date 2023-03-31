package ru.ivanov.librarymvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "Name couldn't be empty")
    @Pattern(regexp = "[А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+ [А-ЯЁA-Z][а-яёa-z]+", message = "Write full name in format: Surname Name Lastname")
    @Size(min = 2, max = 80, message = "Name length couldn't be greater than 80 or less than 2 characters")
    private String name;

    @Column(name = "phone_number")
    @NotEmpty(message = "Phone number couldn't be empty")
    @Pattern(regexp = "\\d{10}", message = "Incorrect phone number, write 10 digits of phone number (without +7 or 8 in the beginning)")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(name = "email")
    @NotEmpty(message = "Email couldn't be empty")
    @Email(message = "Incorrect email, write email in format: yourAddress@example.com")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public String getDateInFormat() {
        return new SimpleDateFormat("dd/MM/yyyy").format(birthDate);
    }
}
