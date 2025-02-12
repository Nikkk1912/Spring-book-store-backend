package com.springbookserver.repository;

import com.springbookserver.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.authors a JOIN b.genres g " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(a.middleName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(g.genre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " )
    Page<Book> searchBooksByTitleOrAuthorOrGenre(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT b FROM Book b " +
            "JOIN b.authors a " +
            "JOIN b.genres g " +
            "WHERE (:title IS NULL OR b.title LIKE %:title%) " +
            "AND (:author IS NULL OR a.firstName LIKE %:author% OR a.lastName LIKE %:author%) " +
            "AND (:genre IS NULL OR g.genre LIKE %:genre%)")
    List<Book> findBooksByFilters(@Param("title") String title,
                                  @Param("author") String author,
                                  @Param("genre") String genre);

}
