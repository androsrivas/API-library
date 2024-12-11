package com.bootcamp.api_library.service;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findBookById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepository.findBookByTitleContaining(title);
    }

    public Optional<List<Book>> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthors(author);
    }

    public Optional<List<Book>> getBooksByGenre(String genre) {
        return bookRepository.findBooksByGenres(genre);
    }
}
