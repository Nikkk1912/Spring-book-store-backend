package com.springbookserver.service;

import com.springbookserver.dao.interfaces.GenreDao;
import com.springbookserver.dto.reques.GenreRequestDto;
import com.springbookserver.dto.response.GenreResponseDto;
import com.springbookserver.model.Genre;
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

    @Override
    public GenreResponseDto createGenre(GenreRequestDto genreRequestDto) {
        Genre genre = new Genre(genreRequestDto.getGenre());
        return DtoMapper.genreToDto(genre);
    }

    @Override
    public GenreResponseDto updateGenre(Long id, GenreRequestDto genreRequestDto) {
        Genre genre = genreDao.getById(id);
        genre.setGenre(genreRequestDto.getGenre());
        genreDao.save(genre);
        return DtoMapper.genreToDto(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreDao.delete(id);
    }
}
