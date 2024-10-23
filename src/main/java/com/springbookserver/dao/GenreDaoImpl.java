package com.springbookserver.dao;

import com.springbookserver.dao.interfaces.GenreDao;
import com.springbookserver.exeption_handling.exceptions.GenreNotFoundException;
import com.springbookserver.model.Genre;
import com.springbookserver.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }

    @Override
    public void delete(long id) {
        genreRepository.delete(getById(id));
    }
}
