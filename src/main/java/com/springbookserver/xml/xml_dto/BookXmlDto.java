package com.springbookserver.xml.xml_dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@NoArgsConstructor
@XmlRootElement(name = "book")
public class BookXmlDto {
    private Long id;
    private String title;
    private List<AuthorXmlDto> authors;
    private List<GenreXmlDto> genres;
    private BigDecimal price;
    private int stock;
    private String coverImageFile;

    @XmlElement
    public Long getId() { return id; }

    @XmlElement
    public String getTitle() { return title; }

    @XmlElement(name = "author")
    public List<AuthorXmlDto> getAuthors() { return authors; }

    @XmlElement(name = "genre")
    public List<GenreXmlDto> getGenres() { return genres; }

    @XmlElement
    public BigDecimal getPrice() { return price; }

    @XmlElement
    public int getStock() { return stock; }

    @XmlElement
    public String getCoverImageFile() { return coverImageFile; }
}

