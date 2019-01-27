package com.library.repository.interfaces;

import com.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional <Author> findByNameAndLastName(String name, String lastName);

}
