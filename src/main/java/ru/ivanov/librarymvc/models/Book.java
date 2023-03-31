package ru.ivanov.librarymvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Title couldn't be empty")
    @Size(min = 2, max = 150, message = "Name length couldn't be greater than 150 or less than 2 characters")
    private String title;

    @Column(name = "author")
    @NotEmpty
    @Pattern(regexp = "[А-ЯЁA-Zа-яa-zё ]+", message = "Name should contains words only")
    @Size(min = 2, max = 80, message = "Author name length couldn't be greater than 80 or less than 2 characters")
    private String author;

    @Column(name = "year")
    @Min(value = 1000, message = "Year of the book should be greater than 1000")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date assignedAt;

    public boolean isExpired() {
        if(owner == null) return false;
        //Если между текущем временем и временем взятия прошло больше 10 суток, то человек просрочил книгу
        return Math.abs(System.currentTimeMillis() - assignedAt.getTime()) > 864000000;
    }
}
