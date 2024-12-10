package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Long, Book> {
    @Query("SELECT b FROM books b WHERE b.title = ?1")
    public Book findBookByContainingTitle(String title);

    @Query("SELECT b FROM books b WHERE b.author = ?1")
    public List<Book> findBooksByContainingAuthor(String author);

    @Query("SELECT b FROM books b WHERE b.genre = ?1")
    public List<Book> findBooksByContainingGenre(String genre);
}
