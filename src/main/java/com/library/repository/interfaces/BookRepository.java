package com.library.repository.interfaces;

import com.library.model.Author;
import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByTitle(String title);

    Optional<Book> findByAuthor(Author author);

    Optional<Book> findByPublishing(String publishing);

    List<Book> findAll();

}
