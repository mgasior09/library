package com.library.controller;

import com.library.model.Release;
import com.library.service.interfaces.ReleaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/releases")
public class ReleaseController {
    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping("/{id}")
    public String printReleasesForBook (@PathVariable("id") Integer bookId, Model model) {
        List<Release> releases =  releaseService.getReleaseByBookId(bookId);
        model.addAttribute("releasesList", releases);
        return "releases";
    }
}
