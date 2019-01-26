package com.library.service;

import com.library.model.Book;
import com.library.repository.interfaces.BookRepository;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultBookService implements BookService {
private  final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getById(Integer bookId) {
        return bookRepository.findById(bookId);
    }


}
