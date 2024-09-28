package com.springbookserver.service.interfaces;

import com.springbookserver.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAll();
    BookResponseDto getById(Long id);
}
