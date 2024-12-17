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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookSearchService {
    private BookRepository bookRepository;

    public BookSearchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Object searchBooks(Optional<String> title, Optional<String> author, Optional<String> genre) {
        Specification<Book> specification = Specification.where(null);

        if (title.isPresent()) {
            specification = specification.and(new TitleSearchSpecification(title.get()).toSpecification());
        }

        if (author.isPresent()) {
            specification = specification.and(new AuthorSearchSpecification(author.get()).toSpecification());
        }

        if (genre.isPresent()) {
            specification = specification.and(new GenreSearchSpecification(genre.get()).toSpecification());
        }

        List<Book> books = bookRepository.findAll(specification);

        if(title.isPresent() || author.isPresent()) {
            return books.stream()
                    .map(book -> new BookDetailsDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getAuthors().stream()
                                    .map(a -> a.getName() + " " + a.getSurname())
                                    .collect(Collectors.toList()),
                            book.getDescription(),
                            book.getGenres()))
                    .collect(Collectors.toList());
        }

        if(genre.isPresent()) {
            return books.stream()
                    .map(book -> new BookSummaryDTO(
                            book.getId(),
                            book.getTitle(),
                            book.getAuthors().stream()
                                    .map(a -> a.getName() + " " + a.getSurname())
                                    .collect(Collectors.toList()),
                            book.getGenres()))
                    .collect(Collectors.toList());
        }

        return  new ApiResponse("No parameters provided. Please, specify a search parameter.");
    }
}






