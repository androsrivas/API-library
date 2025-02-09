package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.DTO.ApiResponse;
import com.bootcamp.api_library.DTO.Book.BookSummaryDTO;
import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.service.Book.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/library/books")
public class BookController {
    private final BookService bookService;
    private final BookSearchService bookSearchService;


    public BookController(BookService bookService, BookSearchService bookSearchService) {
        this.bookService = bookService;
        this.bookSearchService = bookSearchService;
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book newBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(newBook));
    }

    @GetMapping
    public ResponseEntity<List<BookSummaryDTO>> getAll() {
        List<BookSummaryDTO> books = bookService.readAll();
        if (books.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable UUID id) {
        return bookService.readById(id)
                .map(book -> ResponseEntity.status(HttpStatus.OK).body(book))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable UUID id, @RequestBody Book bookDetails) {
        try {
            Book book = bookService.update(id, bookDetails);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (bookService.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBy(
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> genre) {
        Object result = bookSearchService.searchBooks(title, author, genre);

        if(result instanceof ApiResponse) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if (result instanceof List) {
            List<?> bookList = (List<?>) result;
            if(!bookList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(bookList);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse("No parameters provided. Please, specify a search parameter."));
        }
}
