package com.library.service.interfaces;

import com.library.model.Release;

import java.util.List;

public interface ReleaseService {
    List<Release> getReleaseByBookId (Integer bookId);
    Release addReleaseToBook(Release release);
}
