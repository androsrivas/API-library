package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.Book.BookRepository;
import com.bootcamp.api_library.specification.AuthorSearchSpecification;
import com.bootcamp.api_library.specification.GenreSearchSpecification;
import com.bootcamp.api_library.specification.TitleSearchSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BookSearchService {
    private BookRepository bookRepository;

    public List<Book> searchBooks(
            Optional<String> title,
            Optional<String> author,
            Optional<String> genre) {
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
        return bookRepository.findAll(specification);
    }
}
