package com.library.repository.interfaces;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthor_NameAndAuthor_LastName_AndTitle(String name, String lastName, String title);

    List<Book> findByAuthor_NameAndAuthor_LastName_AndPublisher(String name, String lastName, String title);

    List<Book> findByTitleAndPublisher(String Title, String publisher);

    List<Book> findByAuthor_NameAndAuthor_LastName_AndTitleAndPublisher(
            String name, String lastName, String title, String publisher);

    List<Book> findAll();

    void deleteById(Integer id);



}
