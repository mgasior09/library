package com.library.controller;

import com.library.model.Book;
import com.library.repository.interfaces.BookRepository;
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
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/add")
    public String initBookAddForm(Model model) {
        model.addAttribute("addedBook", new Book());
        return "addBook";
    }

    @PostMapping
    public String addBook(@Valid @ModelAttribute("addedBook") Book book, BindingResult br) {
        if (br.hasErrors()) {
            return "addBook";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String initBookEditForm(@PathVariable("id") Integer bookId, Model model) {
        Optional<Book> foundBook = bookService.getById(bookId);
        foundBook.ifPresent(book -> model.addAttribute("editBook", book));
        return "editBook";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book) {
        bookService.editBook(book.getId(), book);
        return "redirect:/books";
    }

    @GetMapping
    public String printAllBooks(Model model) {
        List<Book> bookList = bookService.findAll();
        bookList.sort(Comparator.comparing(Book::getId));
        model.addAttribute("booksList", bookList);
        return "books";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Integer bookId, Model model) {
        bookService.deleteById(bookId);
        return "redirect:/books";
    }
}
