package com.springbookserver.dao.interfaces;

import com.springbookserver.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getById(Long id);
    Book save(Book book);
    Page<Book> findAll(Pageable pageable);
    Page<Book> getByKeyWord(@Param("searchTerm") String searchTerm, Pageable pageable);

}
