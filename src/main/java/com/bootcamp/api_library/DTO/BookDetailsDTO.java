package com.bootcamp.api_library.DTO;

import java.util.List;
import java.util.UUID;

public class BookDetailsDTO {
    private UUID id;
    private String title;
    private List<String> author;
    private String description;
    private List<String> genre;

    public BookDetailsDTO(UUID id, String title, List<String> author, String description, List<String> genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
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

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }
}
