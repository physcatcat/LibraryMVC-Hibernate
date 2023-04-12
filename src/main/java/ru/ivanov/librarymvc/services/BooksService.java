package ru.ivanov.librarymvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.librarymvc.models.Book;
import ru.ivanov.librarymvc.models.Person;
import ru.ivanov.librarymvc.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> findAll(Integer page, Integer limit, String sortBy) {
        return booksRepository.findAll(PageRequest.of(page, limit, Sort.by(sortBy)));
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void save(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = booksRepository.findById(id).get();
        book.setOwner(person);
    }

    @Transactional
    public void release(int id) {
        Book book = booksRepository.findById(id).get();
        book.setOwner(null);
    }
}
