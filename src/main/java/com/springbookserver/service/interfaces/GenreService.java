package com.springbookserver.service.interfaces;

import com.springbookserver.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    List<GenreResponseDto> getAll();
    GenreResponseDto getById(Long id);
}
