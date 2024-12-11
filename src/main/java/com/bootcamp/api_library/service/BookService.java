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

    public Optional<List<Book>> getBookByTitle(String title) {
        List<Book> books = bookRepository.findBookByTitleContaining(title);
        return Optional.ofNullable(books);
    }

    public Optional<List<Book>> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findBooksByAuthors(author);
        return Optional.ofNullable(books);
    }

    public Optional<List<Book>> getBooksByGenre(String genre) {
        return bookRepository.findBooksByGenres(genre);
    }

    public void addBook(Book newBook) {
        bookRepository.save(newBook);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(UUID id, Book bookDetails) {
        Optional<Book> foundBook = bookRepository.findBookById(id);

        if(foundBook.isPresent()) {
            Book book = foundBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthors(bookDetails.getAuthors());
            book.setIsbn(bookDetails.getIsbn());

            return bookRepository.save(book);
        }

        throw new RuntimeException("Book with id " + id + " not found.");
    }
}
