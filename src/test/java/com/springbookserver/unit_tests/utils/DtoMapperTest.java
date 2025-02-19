package com.springbookserver.unit_tests.utils;

import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.dto.response.GenreResponseDto;
import com.springbookserver.utils.DtoMapper;
import com.springbookserver.xml.xml_dto.AuthorXmlDto;
import com.springbookserver.xml.xml_dto.BookXmlDto;
import com.springbookserver.xml.xml_dto.GenreXmlDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class DtoMapperTest {

    private final List<AuthorResponseDto> authorResponseDtoList = List.of(new AuthorResponseDto(1L, "Name-1", null, "Lastname-1"));
    private final List<GenreResponseDto> genreResponseDtoList = List.of(new GenreResponseDto(1L, "Genre-1"));

    private final BookResponseDto bookResponseDto = new BookResponseDto(
            1L,
            "BookTitle-1",
            authorResponseDtoList,
            genreResponseDtoList,
            BigDecimal.TEN,
            12,
            "coverImage-1"
    );

    private final List<AuthorXmlDto> authorXmlDtoList = List.of(new AuthorXmlDto(1L, "Name-1", null, "Lastname-1"));
    private final List<GenreXmlDto> genreXmlDtoList = List.of(new GenreXmlDto(1L, "Genre-1"));

    private final BookXmlDto bookXmlDto = new BookXmlDto(
            1L,
            "BookTitle-1",
            authorXmlDtoList,
            genreXmlDtoList,
            BigDecimal.TEN,
            12,
            "coverImage-1"
    );

    @Test
    void shouldDoConversionAndReturnXmlStringBookCheck() {
        BookXmlDto result = DtoMapper.bookResponseDtoConvertToXmlDto(bookResponseDto);

        Assertions.assertEquals(result.getId(), bookXmlDto.getId());
        Assertions.assertEquals(result.getTitle(), bookXmlDto.getTitle());
        Assertions.assertEquals(result.getPrice(), bookXmlDto.getPrice());
        Assertions.assertEquals(result.getStock(), bookXmlDto.getStock());
        Assertions.assertEquals(result.getCoverImageFile(), bookXmlDto.getCoverImageFile());
    }

    @Test
    void shouldDoConversionAndReturnXmlStringAuthorCheck() {
        AuthorResponseDto expected = authorResponseDtoList.get(0);
        AuthorXmlDto result = DtoMapper.bookResponseDtoConvertToXmlDto(bookResponseDto).getAuthors().get(0);

        Assertions.assertEquals(result.getId(), expected.getId());
        Assertions.assertEquals(result.getFirstName(), expected.getFistName());
        Assertions.assertEquals(result.getMiddleName(), expected.getMiddleName());
        Assertions.assertEquals(result.getLastName(), expected.getLastName());
    }

    @Test
    void shouldDoConversionAndReturnXmlStringGenreCheck() {
        GenreResponseDto expected = genreResponseDtoList.get(0);
        GenreXmlDto result = DtoMapper.bookResponseDtoConvertToXmlDto(bookResponseDto).getGenres().get(0);

        Assertions.assertEquals(result.getId(), expected.getId());
        Assertions.assertEquals(result.getGenre(), expected.getGenre());
    }
}
