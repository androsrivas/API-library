package com.bootcamp.api_library.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(name = "title", nullable = false)
    public String title;

    @ElementCollection
    @CollectionTable(name = "books_authors")
    public List<String> authors;

    @Column(name = "isbn", nullable = false, unique = true)
    public String isbn;

    @Column(name = "description")
    public String description;

    @ElementCollection
    @CollectionTable(name = "books_genres")
    public List<String> genres;

    public Book() {}

    public Book(String title, String author, String isbn, String description, String genre) {
        this.title = title;
        this.authors = Collections.singletonList(author);
        this.isbn = isbn;
        this.description = description;
        this.genres = Collections.singletonList(genre);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
