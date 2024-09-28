package com.springbookserver.dao.interfaces;

import com.springbookserver.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    Author getById(Long id);
    Author save(Author author);
}
