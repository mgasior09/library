package com.library.controller;

import com.library.model.Book;
import com.library.model.Release;
import com.library.service.interfaces.BookService;
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
@RequestMapping("/releases")
public class ReleaseController {
    private final ReleaseService releaseService;
    private final BookService bookService;

    public ReleaseController(ReleaseService releaseService, BookService bookService, VolumeService volumeService) {
        this.releaseService = releaseService;
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public String printReleasesForBook (@PathVariable("id") Integer bookId, Model model) {
        List<Release> releases =  releaseService.getReleaseByBookId(bookId);
        model.addAttribute("releasesList", releases);
        return "releases";
    }

    @GetMapping("/add/{id}")
    public String initReleaseAddForm(@PathVariable("id") Integer bookId, Model model) {
        Release release = new Release();
        Optional<Book> book = bookService.getById(bookId);
        release.setBook(book.get());
        model.addAttribute("addedRelease", release);
        model.addAttribute("bookId", bookId);
        return "addRelease";
    }

    @PostMapping
    public String addRelease(@Valid @ModelAttribute("addedRelease") Release release, BindingResult br) {
        if (br.hasErrors()) {
            return "addRelease";
        }
        releaseService.addReleaseToBook(release);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer releaseId) {
        releaseService.deleteById(releaseId);
        return "redirect:/books";
    }
}
