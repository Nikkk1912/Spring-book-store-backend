package com.springbookserver.service;

import com.springbookserver.dao.interfaces.GenreDao;
import com.springbookserver.dto.response.GenreResponseDto;
import com.springbookserver.service.interfaces.GenreService;
import com.springbookserver.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<GenreResponseDto> getAll() {
        return genreDao.getAll().stream().map(DtoMapper::genreToDto).toList();
    }

    @Override
    public GenreResponseDto getById(Long id) {
        return DtoMapper.genreToDto(genreDao.getById(id));
    }
}
