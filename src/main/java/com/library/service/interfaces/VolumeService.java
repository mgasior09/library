package com.library.service.interfaces;

import com.library.model.Volume;

import java.util.List;

public interface VolumeService {
    Volume addVolumeToRelease(Volume volume);

    List<Volume> getVolumeByReleaseId(Integer releaseId);

    void deleteById(Integer volumeId);
}
