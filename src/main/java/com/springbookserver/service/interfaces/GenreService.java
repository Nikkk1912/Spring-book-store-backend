package com.springbookserver.service.interfaces;

import com.springbookserver.dto.reques.GenreRequestDto;
import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    List<GenreResponseDto> getAll();
    GenreResponseDto getById(Long id);

    GenreResponseDto createGenre(GenreRequestDto genreRequestDto);
    GenreResponseDto updateGenre(Long id, GenreRequestDto genreRequestDto);

    void deleteGenre(Long id);
}
