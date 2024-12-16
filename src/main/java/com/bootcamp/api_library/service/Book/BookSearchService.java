package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class BookSearchService {
    protected List<Book> search(Function<String, List<Book>> searchFunction, String criteria) {
        return searchFunction.apply(criteria);
    }
}
