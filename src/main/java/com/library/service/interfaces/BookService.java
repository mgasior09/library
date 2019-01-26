package com.library.service.interfaces;

import com.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook (Book book);
    Optional<Book> getById (Integer bookId);
    List<Book> findAll();

}
