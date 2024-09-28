package com.springbookserver.dao;

import com.springbookserver.dao.interfaces.BookDao;
import com.springbookserver.exeption_handling.exceptions.BookNotFoundException;
import com.springbookserver.model.Book;
import com.springbookserver.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.saveAndFlush(book);
    }

}
