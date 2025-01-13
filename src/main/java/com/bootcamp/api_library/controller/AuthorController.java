package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.DTO.Author.AuthorDTORequest;
import com.bootcamp.api_library.DTO.Author.AuthorDTOResponse;
import com.bootcamp.api_library.DTO.Author.AuthorMapper;
import com.bootcamp.api_library.model.Author;
import com.bootcamp.api_library.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/library/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorDTOResponse> createAuthor(@RequestBody AuthorDTORequest newAuthor) {
        Author author = AuthorMapper.toEntity(newAuthor);
        Author savedAuthor = authorService.create(author);
        return new ResponseEntity<>(AuthorMapper.toDTOResponse(savedAuthor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTOResponse>> getAllAuthors() {
        List<AuthorDTOResponse> authors = authorService.readAll().stream()
                .map(author -> AuthorMapper.toDTOResponse(author))
                .collect(Collectors.toList());
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable UUID id) {
        return authorService.readById(id)
                .map(author -> ResponseEntity.status(HttpStatus.OK).body(author))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTOResponse> updateAuthor(@PathVariable UUID id,
                                                          @RequestBody AuthorDTORequest authorDetails) {
        try {
            Author author = AuthorMapper.toEntity(authorDetails);
            author.setId(id);
            Author savedAuthor = authorService.update(id, author);
            return new ResponseEntity<>(AuthorMapper.toDTOResponse(savedAuthor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        if (authorService.delete(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/search")
    public List<AuthorDTOResponse> searchAuthorByNameAndSurname(@RequestParam(required = false) String name,
                                                                @RequestParam(required = false) String surname) {
        List<Author> authors = authorService.readByNameAndSurname(name, surname);
        return authors.stream()
                .map(AuthorMapper::toDTOResponse)
                .collect(Collectors.toList());
    }
}
