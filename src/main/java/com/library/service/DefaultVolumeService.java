package com.library.service;

import com.library.model.Volume;
import com.library.repository.interfaces.VolumeRepository;
import com.library.service.interfaces.VolumeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultVolumeService implements VolumeService {
    private final VolumeRepository volumeRepository;

    public DefaultVolumeService(VolumeRepository volumeRepository) {
        this.volumeRepository = volumeRepository;
    }


    @Override
    public Volume addVolumeToRelease(Volume volume) {
        volume.setAdded(new Date());
        return volumeRepository.save(volume);
    }

    @Override
    public List<Volume> getVolumeByReleaseId(Integer releaseId) {
        return volumeRepository.findByRelease_Id(releaseId);
    }

    @Override
    public void deleteById(Integer volumeId) {
        volumeRepository.deleteById(volumeId);
    }

    @Override
    public void setReservation(Integer volumeId) {
        Optional<Volume> foundVolume = volumeRepository.findById(volumeId);
        foundVolume.get().setReserved(true);
        volumeRepository.save(foundVolume.get());
    }
}
