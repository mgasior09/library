package com.library.service;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.User;
import com.library.model.UserRole;
import com.library.repository.interfaces.AuthorRepository;
import com.library.repository.interfaces.BookRepository;
import com.library.repository.interfaces.UserRepository;
import com.library.repository.interfaces.UserRoleRepository;
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
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    public DefaultBookService(BookRepository bookRepository, AuthorRepository authorRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
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
        Book book = bookRepository.findById(bookId).get();
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

    @Override
    public String findRoleByUserName(String userName) {
        Optional<User> foundUser = userRepository.findByUsername(userName);
        UserRole userRole = userRoleRepository.findUserRoleByUser(foundUser.get()).get();
        return userRole.getRoleName();
    }

    @Override
    public List<Book> findBookByAuthorAndTitle(String name, String lastName, String title) {
        return bookRepository.findByAuthor_NameAndAuthor_LastName_AndTitle(name, lastName, title);
    }

    @Override
    public List<Book> findBookByAuthorAndPublisher(String name, String lastName, String publisher) {
        return bookRepository.findByAuthor_NameAndAuthor_LastName_AndPublisher(name, lastName, publisher);
    }


    @Override
    public List<Book> findBookByTitleAndPublisher(String title, String publisher) {
        return bookRepository.findByTitleAndPublisher(title, publisher);
    }

    @Override
    public List<Book> findBookByAuthorAndTitleAndPublisher(String name, String lastName, String title, String publisher) {
        return bookRepository.findByAuthor_NameAndAuthor_LastName_AndTitleAndPublisher(
                name, lastName, title, publisher);
    }
}
