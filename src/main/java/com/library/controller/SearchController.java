package com.library.controller;

import com.library.model.Book;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/byTitle")
    public String searchByTitle(String title, Model model) {
        List<Book> bookList = bookService.findByTitle(title);
        model.addAttribute("bookList", bookList);
        return "search";
    }

}
