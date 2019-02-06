package com.library.controller;

import com.library.model.Author;
import com.library.model.Book;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final BookService bookService;

    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/go")
    public String printSearch(Model model) {
        return "search";
    }

    @GetMapping
    public String printStartPage(Model model, Principal principal) {
        String userName = principal.getName();
        String userRole = bookService.findRoleByUserName(userName);
        if (userRole.equals("ROLE_WORKER")) {
            return "redirect:/customers";
        } else if (userRole.equals("ROLE_ADMIN")) {
            return "redirect:/workers";
        } else if (userRole.equals("ROLE_USER")) {
            return "redirect:/search/go";
        } else {
            return "search/go";
        }
    }

    @GetMapping("/notByIsbn")
    public String searchBook(
            @ModelAttribute("title") String title,
            @ModelAttribute("publisher") String publisher,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastName,
            Model model) {

        if (name.equals("") && lastName.equals("")) {
        List<Book> books = bookService.findBookByTitleAndPublisher(title, publisher);
        model.addAttribute("booksList", books);
        } else if (publisher.equals("")) {
            List<Book> books = bookService.findBookByAuthorAndTitle(name, lastName, title);
            model.addAttribute("booksList", books);
        } else if (title.equals("")) {
            List<Book> books = bookService.findBookByAuthorAndPublisher(name, lastName, publisher);
            model.addAttribute("booksList", books);
        } else if (!name.equals("") && !lastName.equals("") && !publisher.equals("") && !title.equals("")) {
            List<Book> books = bookService.findBookByAuthorAndTitleAndPublisher(name, lastName, title, publisher);
            model.addAttribute("booksList", books);
        } else {
            List<Book> books = new ArrayList<>();
            model.addAttribute("booksList", books);
        }
        return "foundBooks";
    }

    @GetMapping("/byIsbn")
    public String searchByIsbn(@ModelAttribute("isbn") String isbn, Model model) {
        Optional<Book> foundBook = bookService.findByIsbn(isbn);
        List<Book> books = new ArrayList<>();
        foundBook.ifPresent(book -> books.add(foundBook.get()));
        model.addAttribute("booksList", books);
        return "foundBooks";
    }


}
