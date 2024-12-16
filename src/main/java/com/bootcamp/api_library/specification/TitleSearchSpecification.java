package com.bootcamp.api_library.specification;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class TitleSearchSpecification implements SearchSpecification{
    private String title;

    public TitleSearchSpecification(String title) {
        this.title = title;
    }

    @Override
    public Specification<Book> toSpecification() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }
}
