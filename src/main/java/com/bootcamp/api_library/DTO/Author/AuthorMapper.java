package com.bootcamp.api_library.DTO.Author;

import com.bootcamp.api_library.model.Author;

public class AuthorMapper {
    public static Author toEntity(AuthorDTORequest authorDTORequest) {
        return new Author(
                authorDTORequest.name(),
                authorDTORequest.surname()
        );
    }

    public static AuthorDTORequest toDTORequest (Author author) {
        return new AuthorDTORequest(
                author.getName(),
                author.getSurname()
        );
    }

    public static AuthorDTOResponse toDTOResponse(Author author) {
        return new AuthorDTOResponse(
                author.getName(),
                author.getSurname()
        );
    }
}
