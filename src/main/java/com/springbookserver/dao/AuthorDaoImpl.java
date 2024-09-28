package com.springbookserver.dao;

import com.springbookserver.dao.interfaces.AuthorDao;
import com.springbookserver.exeption_handling.exceptions.AuthorNotFoundException;
import com.springbookserver.model.Author;
import com.springbookserver.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author save(Author author) {
        return authorRepository.saveAndFlush(author);
    }
}
