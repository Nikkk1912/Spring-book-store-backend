package com.springbookserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first-name")
    private String firstName;
    @Column(name = "middle-name")
    private String middleName;
    @Column(name = "last-name")
    private String lastName;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
