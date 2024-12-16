package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.service.Book.BookByAuthorService;
import com.bootcamp.api_library.service.Book.BookByGenreService;
import com.bootcamp.api_library.service.Book.BookByTitleService;
import com.bootcamp.api_library.service.Book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;
    private final BookByTitleService bookByTitleService;
    private final BookByAuthorService bookByAuthorService;
    private final BookByGenreService bookByGenreService;


    public BookController(BookService bookService, BookByTitleService bookByTitleService, BookByAuthorService bookByAuthorService, BookByGenreService bookByGenreService) {
        this.bookService = bookService;
        this.bookByTitleService = bookByTitleService;
        this.bookByAuthorService = bookByAuthorService;
        this.bookByGenreService = bookByGenreService;
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book newBook) {
        return ResponseEntity.status(201).body(bookService.createBook(newBook));
    }

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable UUID id) {
        return bookService.getBookById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable UUID id, @RequestBody Book bookDetails) {
        try {
            Book book = bookService.updateBook(id, bookDetails);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getByTitle(@PathVariable String title) {
        List<Book> books = bookByTitleService.findByTitle(title);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }
}
