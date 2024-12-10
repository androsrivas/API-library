package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Long, Book> {
    public Book findBookByTitle(String title);

    public List<Book> findBooksByAuthor(String author);

    public List<Book> findBooksByGenre(String genre);
}
