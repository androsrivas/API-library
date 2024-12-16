package com.bootcamp.api_library.specification;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSearchSpecification implements SearchSpecification {
    private String author;

    public AuthorSearchSpecification(String author) {
        this.author = author;
    }

    @Override
    public Specification<Book> toSpecification() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }
}
