package ru.ivanov.librarymvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivanov.librarymvc.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
