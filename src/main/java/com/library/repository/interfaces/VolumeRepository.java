package com.library.repository.interfaces;

import com.library.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolumeRepository extends JpaRepository<Volume, Integer> {

    Optional<Volume> findById(Integer volumeId);

    List<Volume> findByRelease_Id(Integer releaseId);

    void deleteById(Integer volumeId);


}
