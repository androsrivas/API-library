package com.bootcamp.api_library.respository.Book;

import com.bootcamp.api_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookAuthorRepository extends JpaRepository<Book, UUID> {
    List<Book> findBookByAuthorContainingIgnoreCase(String author);
}
