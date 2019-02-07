package com.library.service;

import com.library.model.Release;
import com.library.repository.interfaces.BookRepository;
import com.library.repository.interfaces.ReleaseRepository;
import com.library.service.interfaces.ReleaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultReleaseService implements ReleaseService {
    private final ReleaseRepository releaseRepository;
    private final BookRepository bookRepository;

    public DefaultReleaseService(ReleaseRepository releaseRepository, BookRepository bookRepository) {
        this.releaseRepository = releaseRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Release> getReleaseByBookId(Integer bookId) {
        return releaseRepository.findByBook_Id(bookId);
    }

    @Override
    public Release addReleaseToBook(Release release) {
        return null;
    }
}
