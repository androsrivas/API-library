package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.DTO.BookSummaryDTO;
import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.Book.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<BookSummaryDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new BookSummaryDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthors() != null ? new ArrayList<>(book.getAuthors()) : Collections.emptyList(),
                        book.getGenres() != null ? book.getGenres() : Collections.emptyList()))
                .collect(Collectors.toList());
    }

    public Optional<Book> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(UUID id, Book bookDetails) {
        Optional<Book> foundBook = bookRepository.findById(id);

        if(foundBook.isPresent()) {
            Book book = foundBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthors(bookDetails.getAuthors());
            book.setIsbn(bookDetails.getIsbn());
            book.setDescription(bookDetails.getDescription());
            book.setGenres(bookDetails.getGenres());

            return bookRepository.save(book);
        }

        throw new RuntimeException("Book with id " + id + " not found.");
    }

    public boolean deleteBook(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return  false;
    }
}
