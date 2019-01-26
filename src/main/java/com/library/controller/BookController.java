package com.library.controller;

import com.library.model.Book;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/edit")
    public String editBook(Integer bookId) {
        Optional<Book> optionalBook = bookService.getById(bookId);
        return "books";
    }

    @GetMapping("/add")
    public String initBookAddForm (Model model){
        model.addAttribute("addedBook", new Book());
        return "addBook";
    }

    @PostMapping
    public String saveBook (@Valid @ModelAttribute("addedBook") Book book, BindingResult br) {
        if (br.hasErrors()) {
            return "addBook";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }
}
