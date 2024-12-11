package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findBookById(UUID id);

    List<Book> findBookByTitleContaining(String title);

    List<Book> findBooksByAuthors(String author);

    Optional<List<Book>> findBooksByGenres(String genre);
}
