package com.library.service.interfaces;

import com.library.model.Author;
import com.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(Book book);
    void editBook(Integer bookId, Book book);
    void deleteById(Integer id);

    Optional<Book> getById(Integer bookId);

    List<Book> findAll();

    Optional<Book> findByIsbn(String isbn);

    String findRoleByUserName(String userName);

    List<Book> findBookByAuthorAndTitle (String name, String lastName, String title);

    List<Book> findBookByAuthorAndPublisher (String name, String lastName, String publisher);

    List<Book> findBookByTitleAndPublisher(String title, String publisher);

    List<Book> findBookByAuthorAndTitleAndPublisher (String name, String lastName, String title, String publisher);
}
