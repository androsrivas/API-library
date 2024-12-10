package com.bootcamp.api_library.service;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findBookByTitleContaining(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthorsContaining(author);
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findBooksByGenresContaining(genre);
    }
}
