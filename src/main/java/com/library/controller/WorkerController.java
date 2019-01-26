package com.library.controller;

import com.library.model.Book;
import com.library.service.interfaces.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class WorkerController {
    private final BookService bookService;

    public WorkerController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public String editBook(Integer bookId) {
        Optional<Book> optionalBook = bookService.getById(bookId);

        return "";
    }

    @PostMapping
    public String saveBook (Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }
}
