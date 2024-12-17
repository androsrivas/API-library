package com.bootcamp.api_library.DTO;

import com.bootcamp.api_library.model.Author;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BookDetailsDTO {
    private UUID id;
    private String title;
    private List<String> authors;
    private String description;
    private List<String> genres;

    public BookDetailsDTO(UUID id, String title, List<String> authors, String description, List<String> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.genres = genres;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
