package com.springbookserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "genre")
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    public Genre(String genre) {
        this.genre = genre;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
