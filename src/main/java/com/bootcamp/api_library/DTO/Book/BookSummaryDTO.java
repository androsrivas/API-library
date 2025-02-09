package com.bootcamp.api_library.DTO.Book;

import java.util.List;
import java.util.UUID;

public class BookSummaryDTO {
    private UUID id;
    private String title;
    private List<String> authors;
    private List<String> genre;

    public BookSummaryDTO(UUID id, String title, List<String> authors, List<String> genre) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
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

    public List<String> getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
