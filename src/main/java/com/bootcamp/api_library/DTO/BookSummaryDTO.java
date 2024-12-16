package com.bootcamp.api_library.DTO;

import java.util.List;
import java.util.UUID;

public class BookSummaryDTO {
    private UUID id;
    private String title;
    private List<String> author;
    private List<String> genre;

    public BookSummaryDTO(UUID id, String title, List<String> author, List<String> genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return author;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
