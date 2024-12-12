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

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "books_genres")
    private List<String> genres;

    public Book() {}

    public Book(String title, Author author, String isbn, String description, String genre) {
        this.title = title;
        this.authors = (Set<Author>) author;
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

    public Set<Author> getAuthors() {
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

    public void setAuthors(Set<Author> authors) {
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
