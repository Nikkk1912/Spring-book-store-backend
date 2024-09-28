package com.springbookserver.dao.interfaces;

import com.springbookserver.model.Author;
import com.springbookserver.model.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
    Genre getById(Long id);
    Genre save(Genre genre);
}
