package com.library.service;

import com.library.model.Author;
import com.library.repository.interfaces.AuthorRepository;
import com.library.service.interfaces.AuthorService;

public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository authorRepository;

    public DefaultAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
}
