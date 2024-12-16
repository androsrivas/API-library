package com.bootcamp.api_library.service.Book;

import com.bootcamp.api_library.model.Book;
import com.bootcamp.api_library.respository.Book.BookGenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookByGenreService extends BookSearchService {
    private BookGenreRepository bookGenreRepository;

    public List<Book> findByGenre(String genre) {
        return search(bookGenreRepository::findBooksBygenresContaining, genre);
    }
}
