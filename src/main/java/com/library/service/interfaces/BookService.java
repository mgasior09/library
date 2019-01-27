package com.library.service.interfaces;

import com.library.model.Author;
import com.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(Book book);
    void editBook(Integer bookId);
    void deleteById(Integer id);

    Optional<Book> getById(Integer bookId);

    List<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findByPublishing(String publishing);
}
