package com.bootcamp.api_library.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "books_genres")
    private List<String> genres;

}
