package com.library.controller;

import com.library.model.Release;
import com.library.model.Volume;
import com.library.service.interfaces.ReleaseService;
import com.library.service.interfaces.VolumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/volumes")
public class VolumeController {
    private final ReleaseService releaseService;
    private final VolumeService volumeService;

    public VolumeController(ReleaseService releaseService, VolumeService volumeService) {
        this.releaseService = releaseService;
        this.volumeService = volumeService;
    }

    @GetMapping("/{id}")
    public String printVolumesForReleases(@PathVariable("id") Integer releaseId, Model model) {
        List<Volume> volumes = volumeService.getVolumeByReleaseId(releaseId);
        model.addAttribute("volumesList", volumes);
        return "volumes";
    }

    @GetMapping("/add/{id}")
    public String initVolumeAddForm(@PathVariable("id") Integer releaseId, Model model) {
        Volume volume = new Volume();
        model.addAttribute("addedVolume", volume);
        Optional<Release> release = releaseService.getById(releaseId);
        volume.setRelease(release.get());
        model.addAttribute("releaseId", releaseId);
        return "addVolume";
    }

    @PostMapping
    public String addRelease(@Valid @ModelAttribute("addedVolume") Volume volume, BindingResult br) {
        if (br.hasErrors()) {
            return "addVolume";
        }
        volumeService.addVolumeToRelease(volume);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer volumeId) {
        volumeService.deleteById(volumeId);
        return "redirect:/books";
    }
}
