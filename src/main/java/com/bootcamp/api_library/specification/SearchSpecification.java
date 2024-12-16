package com.bootcamp.api_library.specification;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.domain.Specification;

public interface SearchSpecification {
    Specification<Book> toSpecification();
}
