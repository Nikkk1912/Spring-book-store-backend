package com.springbookserver.xml.xml_dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "genre")
public class GenreXmlDto {
    private Long id;
    private String genre;

    @XmlElement
    public Long getId() { return id; }

    @XmlElement
    public String getGenre() { return genre; }
}
