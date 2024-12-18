package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.DTO.ApiResponse;
import com.bootcamp.api_library.DTO.BookDetailsDTO;
import com.bootcamp.api_library.DTO.BookSummaryDTO;
import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.Book.BookRepository;
import com.bootcamp.api_library.specification.AuthorSearchSpecification;
import com.bootcamp.api_library.specification.GenreSearchSpecification;
import com.bootcamp.api_library.specification.TitleSearchSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookSearchService {
    private final BookRepository bookRepository;

    public BookSearchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Object searchBooks(Optional<String> title, Optional<String> author, Optional<String> genre) {

        if (title.isEmpty() && author.isEmpty() && genre.isEmpty()) {
            return new ApiResponse("Please provide at least one search parameter.");
        }

        Specification<Book> specification = Specification.where(null);

        if (title.isPresent() && !title.get().isEmpty()) {
            specification = specification.and(new TitleSearchSpecification(title.get()).toSpecification());
        }

        if (author.isPresent() && !author.get().isEmpty()) {
            specification = specification.and(new AuthorSearchSpecification(author.get()).toSpecification());
        }

        if (genre.isPresent() && !genre.get().isEmpty()) {
            specification = specification.and(new GenreSearchSpecification(genre.get()).toSpecification());
        }

        List<Book> books = bookRepository.findAll(specification);

        if(books.isEmpty()) {
            return new ApiResponse("No books found matching the search criteria.");
        }

        if (title.isPresent() || author.isPresent()) {
            return books.stream()
                    .map(book -> new BookDetailsDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getAuthors() != null ? book.getAuthors() : Collections.emptyList(),
                            book.getDescription(),
                            book.getGenres() != null ? book.getGenres() : Collections.emptyList()
                    ))
                    .collect(Collectors.toList());
        }

        if (genre.isPresent()) {
            return books.stream()
                    .map(book -> new BookSummaryDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getAuthors() != null ? book.getAuthors() : Collections.emptyList(),
                            book.getGenres() != null ? book.getGenres() : Collections.emptyList()
                    ))
                    .collect(Collectors.toList());
        }
        return new ApiResponse("No parameters provided. Please, specify a search parameter.");
    }
}