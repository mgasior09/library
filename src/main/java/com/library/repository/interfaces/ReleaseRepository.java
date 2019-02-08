package com.library.repository.interfaces;

import com.library.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {
    List<Release> findByBook_Id(Integer bookId);
    void deleteById(Integer releaseId);
    Optional<Release> findById(Integer releaseId);
}
