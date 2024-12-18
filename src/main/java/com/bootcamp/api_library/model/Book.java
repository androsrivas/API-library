package com.bootcamp.api_library.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "book_authors")
    @Column(name = "author_name")
    private List<String> authors = new ArrayList<>();

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "books_genres")
    private List<String> genres;

    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<Member> members = new HashSet<>();

    public Book() {}

    public Book(String title, List<String> authors, String isbn, String description, String genre) {
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.description = description;
        this.genres = genres != null ? genres : new ArrayList<>();
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
