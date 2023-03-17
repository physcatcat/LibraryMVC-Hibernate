package ru.ivanov.librarymvc.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    @NotEmpty(message = "Title couldn't be empty")
    @Size(min = 2, max = 150, message = "Name length couldn't be greater than 150 or less than 2 characters")
    private String title;

    @NotEmpty
    @Pattern(regexp = "[А-ЯЁA-Zа-яa-zё ]+", message = "Name should contains words only")
    @Size(min = 2, max = 80, message = "Author name length couldn't be greater than 80 or less than 2 characters")
    private String author;

    @Min(value = 1000, message = "Year of the book should be greater than 1000")
    private int year;
}
