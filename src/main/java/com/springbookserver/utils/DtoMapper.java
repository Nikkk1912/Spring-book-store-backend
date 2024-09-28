package com.springbookserver.utils;

import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.dto.response.GenreResponseDto;
import com.springbookserver.model.Author;
import com.springbookserver.model.Book;
import com.springbookserver.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoMapper {

    public static BookResponseDto bookToDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());
        bookResponseDto.setStock(book.getStock());

        List<AuthorResponseDto> authorResponseDtoList =
                book.getAuthors().stream()
                        .map(DtoMapper::authorToDto)
                        .toList();

        List<GenreResponseDto> genreResponseDtoList =
                book.getGenres().stream()
                        .map(DtoMapper::genreToDto)
                        .toList();

        bookResponseDto.setAuthors(authorResponseDtoList);
        bookResponseDto.setGenres(genreResponseDtoList);
        bookResponseDto.setCoverImageFile(book.getCoverImageFile());

        return bookResponseDto;
    }

    public static AuthorResponseDto authorToDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setFistName(author.getFirstName());
        authorResponseDto.setMiddleName(author.getMiddleName());
        authorResponseDto.setLastName(author.getLastName());
        return authorResponseDto;
    }

    public static GenreResponseDto genreToDto(Genre genre) {
        GenreResponseDto genreResponseDto = new GenreResponseDto();
        genreResponseDto.setId(genre.getId());
        genreResponseDto.setGenre(genre.getGenre());
        return genreResponseDto;
    }
}
