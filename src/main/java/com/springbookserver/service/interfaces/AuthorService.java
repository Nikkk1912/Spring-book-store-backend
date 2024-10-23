package com.springbookserver.service.interfaces;

import com.springbookserver.dto.reques.AuthorRequestDto;
import com.springbookserver.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    List<AuthorResponseDto> getAll();
    AuthorResponseDto getById(Long id);

    AuthorResponseDto createAuthor(AuthorRequestDto authorRequestDto);
    AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long id);
}
