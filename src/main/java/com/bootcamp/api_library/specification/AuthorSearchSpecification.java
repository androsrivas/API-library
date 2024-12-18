package com.bootcamp.api_library.specification;

import com.bootcamp.api_library.model.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSearchSpecification implements SearchSpecification {
    private String author;

    public AuthorSearchSpecification(String author) {
        this.author = author;
    }

    @Override
    public Specification<Book> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            Predicate likePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("authors")),
                    "%" + author.toLowerCase() + "%"
            );

            Predicate memberPredicate = criteriaBuilder.isMember(author, root.get("authors"));

            return criteriaBuilder.or(likePredicate, memberPredicate);
        };
    }
}
