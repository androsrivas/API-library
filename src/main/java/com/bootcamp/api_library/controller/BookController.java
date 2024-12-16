package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.service.Book.*;
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
    private final BookSearchService bookSearchService;


    public BookController(BookService bookService, BookSearchService bookSearchService) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book newBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(newBook));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable UUID id) {
        return bookService.getBookById(id)
                .map(book -> ResponseEntity.status(HttpStatus.OK).body(book))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBy(
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> genre) {
        List<Book> books = bookSearchService.searchBooks(title, author, genre);

        return books.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
