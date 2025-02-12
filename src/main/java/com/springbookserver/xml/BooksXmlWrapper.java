package com.springbookserver.xml;

import com.springbookserver.xml.xml_dto.BookXmlDto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@XmlRootElement(name = "books")
public class BooksXmlWrapper {

    private List<BookXmlDto> books;

    public BooksXmlWrapper(List<BookXmlDto> books) {
        this.books = books;
    }

    @XmlElement(name = "book")
    public List<BookXmlDto> getBooks() {
        return books;
    }

}

