package com.bootcamp.api_library.respository;

import com.bootcamp.api_library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findByNameContainingIgnoreCase(String name);
    @Query("SELECT a FROM Author a WHERE " +
            "(:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:surname IS NULL OR LOWER(a.surname) LIKE LOWER(CONCAT('%', :surname, '%')))")
    List<Author> findByNameAndSurname(String name, String surname);
}
