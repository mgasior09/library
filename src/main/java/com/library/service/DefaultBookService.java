package com.library.service;

import com.library.model.Author;
import com.library.model.Book;
import com.library.repository.interfaces.AuthorRepository;
import com.library.repository.interfaces.BookRepository;
import com.library.service.interfaces.AuthorService;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }

    @Override
    public Book addBook(Book book) {
        AuthorService authorService = new DefaultAuthorService(authorRepository);
        Author author = book.getAuthor();
        Optional<Author> optionalAuthor = authorRepository.findByNameAndLastName(author.getName(), author.getLastName());
        if (optionalAuthor.isPresent()) {
            book.setAuthor(optionalAuthor.get());
        } else {
            authorService.addAuthor(author);
        }
        book.setAdded(new Date());
        return bookRepository.save(book);
    }

    @Override
    public void editBook(Integer bookId) {
        Book book = bookRepository.getOne(bookId);
        book.setModified(new Date());
        bookRepository.save(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> getById(Integer bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleLike(title);
    }

    public List<Book> findByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }
}
