package com.library.service;

import com.library.model.Release;
import com.library.repository.interfaces.ReleaseRepository;
import com.library.service.interfaces.ReleaseService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultReleaseService implements ReleaseService {
    private final ReleaseRepository releaseRepository;

    public DefaultReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public List<Release> getReleaseByBookId(Integer bookId) {
        return releaseRepository.findByBook_Id(bookId);
    }

    @Override
    public Release addReleaseToBook(Release release) {
        release.setAdded(new Date());
        return releaseRepository.save(release);
    }

    @Override
    public void deleteById(Integer releaseId) {
        releaseRepository.deleteById(releaseId);
    }

    @Override
    public Optional<Release> getById(Integer releaseId) {
        return releaseRepository.findById(releaseId);
    }
}
