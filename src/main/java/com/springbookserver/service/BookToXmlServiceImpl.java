package com.springbookserver.service;

import com.springbookserver.dto.response.BookResponseDto;
import com.springbookserver.service.interfaces.BookService;
import com.springbookserver.service.interfaces.BookToXmlService;
import com.springbookserver.utils.ContentToFileWriter;
import com.springbookserver.xml.BooksXmlWrapper;
import com.springbookserver.xml.xml_dto.AuthorXmlDto;
import com.springbookserver.xml.xml_dto.BookXmlDto;
import com.springbookserver.xml.xml_dto.GenreXmlDto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookToXmlServiceImpl implements BookToXmlService {

    private final BookService bookService;

    public String filterAndSaveBooksToXml(int pageNum, int pageSize, String searchWord, String savePath) throws IOException, JAXBException {
        Page<BookResponseDto> books = bookService.getByKeyWord(pageNum, pageSize, searchWord);

        List<BookXmlDto> xmlBooks = books.getContent().stream().map(this::convertToXmlDto)
                .collect(Collectors.toList());
        BooksXmlWrapper booksWrapper = new BooksXmlWrapper(xmlBooks);

        JAXBContext context = JAXBContext.newInstance(BooksXmlWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(booksWrapper, writer);
        String xmlContent = writer.toString();

        Path pathToXml = ContentToFileWriter.saveStringToFile(savePath, xmlContent);

        return pathToXml.toString();
    }

    private BookXmlDto convertToXmlDto(BookResponseDto dto) {
        BookXmlDto xmlDto = new BookXmlDto();
        xmlDto.setId(dto.getId());
        xmlDto.setTitle(dto.getTitle());
        xmlDto.setPrice(dto.getPrice());
        xmlDto.setStock(dto.getStock());
        xmlDto.setCoverImageFile(dto.getCoverImageFile());

        List<AuthorXmlDto> authors = dto.getAuthors().stream().map(author -> {
            AuthorXmlDto xmlAuthor = new AuthorXmlDto();
            xmlAuthor.setId(author.getId());
            xmlAuthor.setFirstName(author.getFistName());
            xmlAuthor.setMiddleName(author.getMiddleName());
            xmlAuthor.setLastName(author.getLastName());
            return xmlAuthor;
        }).collect(Collectors.toList());
        xmlDto.setAuthors(authors);

        List<GenreXmlDto> genres = dto.getGenres().stream().map(genre -> {
            GenreXmlDto xmlGenre = new GenreXmlDto();
            xmlGenre.setId(genre.getId());
            xmlGenre.setGenre(genre.getGenre());
            return xmlGenre;
        }).collect(Collectors.toList());
        xmlDto.setGenres(genres);

        return xmlDto;
    }
}
