package com.library.service.interfaces;

import com.library.model.Release;

import java.util.List;
import java.util.Optional;

public interface ReleaseService {
    List<Release> getReleaseByBookId(Integer bookId);

    Release addReleaseToBook(Release release);

    void deleteById(Integer releaseId);

    Optional<Release> getById(Integer releaseId);
}
