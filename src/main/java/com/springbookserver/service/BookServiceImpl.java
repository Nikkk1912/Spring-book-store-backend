package com.springbookserver.service;

import com.springbookserver.dao.interfaces.BookDao;
import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.service.interfaces.BookService;
import com.springbookserver.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;


    @Override
    public List<BookResponseDto> getAll() {
        return bookDao.getAll().stream().map(DtoMapper::bookToDto).toList();
    }

    @Override
    public BookResponseDto getById(Long id) {
        return DtoMapper.bookToDto(bookDao.getById(id));
    }
}
