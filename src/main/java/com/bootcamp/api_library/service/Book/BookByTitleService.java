package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.Book.BookTitleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookByTitleService extends BookSearchService {
    private BookTitleRepository bookTitleRepository;

    public List<Book> findByTitle(String title) {
        return search(bookTitleRepository::findBookByTitleContaining, title);
    }
}
