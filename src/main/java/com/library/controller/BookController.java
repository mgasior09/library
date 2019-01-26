package com.library.controller;

import com.library.model.Book;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Integer bookId) {
        Optional<Book> optionalBook = bookService.getById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

        }
        return "books";
    }

    @GetMapping("/add")
    public String initBookAddForm(Model model) {
        model.addAttribute("addedBook", new Book());
        return "addBook";
    }

    @GetMapping("/{id}/edit")
    public String initBookEditForm(@PathVariable("id")Integer bookId, Model model) {
        Optional<Book> foundBook = bookService.getById(bookId);
        foundBook.ifPresent(book -> model.addAttribute("editBook", book));
        return "editBook";
    }

    @PostMapping
    public String saveBook(@Valid @ModelAttribute("addedBook") Book book, BindingResult br) {
        if (br.hasErrors()) {
            return "addBook";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping
    public String printAllBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        bookList.sort(Comparator.comparing(Book::getId));
        model.addAttribute("booksList", bookList);
        return "books";
    }
}
