package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%")
    Book findBookByTitleContaining(String title);

    @Query("SELECT b FROM Book b WHERE ?1 MEMBER OF b.authors")
    List<Book> findBooksByAuthorsContaining(String author);

    @Query("SELECT b FROM Book b WHERE ?1 MEMBER OF b.genres")
    List<Book> findBooksByGenresContaining(String genre);
}
