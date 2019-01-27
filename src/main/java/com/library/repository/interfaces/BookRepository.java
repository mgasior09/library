package com.library.repository.interfaces;

import com.library.model.Author;
import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitleLike(String title);

    List<Book> findByAuthor(Author author);

    List<Book> findByPublishing(String publishing);

    List<Book> findAll();

    void deleteById(Integer id);

    List<Book> findAll();

}
