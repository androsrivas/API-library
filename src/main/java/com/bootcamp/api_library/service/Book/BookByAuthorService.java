package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.Book.BookAuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookByAuthorService extends BookSearchService {
    private BookAuthorRepository bookAuthorRepository;

    public List<Book> findByAuthor(String author) {
        return search(bookAuthorRepository::findBookByAuthorContaining, author);
    }
}
