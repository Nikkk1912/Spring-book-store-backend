package com.springbookserver.service;

import com.springbookserver.dao.interfaces.AuthorDao;
import com.springbookserver.dto.response.AuthorResponseDto;
import com.springbookserver.service.interfaces.AuthorService;
import com.springbookserver.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public List<AuthorResponseDto> getAll() {
        return authorDao.getAll().stream().map(DtoMapper::authorToDto).toList();
    }

    @Override
    public AuthorResponseDto getById(Long id) {
        return DtoMapper.authorToDto(authorDao.getById(id));
    }
}
