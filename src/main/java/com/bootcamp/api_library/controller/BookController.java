package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/books/title")
    public Book getBookByTitle(@RequestParam String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping("/books/author")
    public List<Book> getBooksByAuthors(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/books/genre")
    public List<Book> getBooksByGenre(@RequestParam String genre) {
        return bookService.getBooksByGenre(genre);
    }
}
