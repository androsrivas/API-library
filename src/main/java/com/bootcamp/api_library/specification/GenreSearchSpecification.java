package com.bootcamp.api_library.specification;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class GenreSearchSpecification implements SearchSpecification {
    private String genre;

    public GenreSearchSpecification(String genre) {
        this.genre = genre;
    }

    @Override
    public Specification<Book> toSpecification() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isMember(genre, root.get("genres"));
    }
}
