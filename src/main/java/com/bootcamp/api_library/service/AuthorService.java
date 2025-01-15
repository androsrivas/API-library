package com.bootcamp.api_library.service;

import com.bootcamp.api_library.exceptions.ResourceNotFoundException;
import com.bootcamp.api_library.model.Author;
import com.bootcamp.api_library.respository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> readAll() {
        return authorRepository.findAll();
    }

    public Optional<Author> readById(UUID id) {
        return authorRepository.findById(id);
    }

    public List<Author> readByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name, surname);
    }

    public Author update(UUID id, Author authorDetails) {
        Optional<Author> foundAuthor = authorRepository.findById(id);

        if (foundAuthor.isEmpty()) throw new ResourceNotFoundException("Author not found.");

        Author author = foundAuthor.get();
        author.setName(authorDetails.getName());
        author.setSurname(authorDetails.getSurname());
        return authorRepository.save(author);
    }

    public Boolean delete(UUID id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
