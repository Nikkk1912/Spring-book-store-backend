package com.springbookserver.dao.interfaces;

import com.springbookserver.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(Long id);
    Book save(Book book);
}
