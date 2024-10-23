package com.springbookserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_authors",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();
    private BigDecimal price;
    private int stock;
    @Column(name="cover-file-name")
    private String coverImageFile;

    public void addAuthor(Author author) {
        authors.add(author);
        author.addBook(this);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.addBook(this);
    }

    public Book(String title, List<Author> authors, List<Genre> genres, BigDecimal price, int stock, String coverImageFile) {
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.price = price;
        this.stock = stock;
        this.coverImageFile = coverImageFile;
    }
}
