package com.springbookserver.controller;

import com.springbookserver.service.interfaces.BookToXmlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

/**
 * Controller to handle book XML generation requests.
 */
@RestController
@RequestMapping("/books")
public class BookXmlController {

    /**
     * Path to save the generated XML file (configured in application.yml).
     */
    @Value("${path.xml.save}")
    private String savePath;

    private final BookToXmlService bookToXmlService;

    /**
     * Constructs the controller with BookToXmlService dependency.
     *
     * @param bookToXmlService Service for converting books to XML.
     */
    public BookXmlController(BookToXmlService bookToXmlService) {
        this.bookToXmlService = bookToXmlService;
    }

    /**
     * Endpoint to retrieve books as XML, filtered by search word and paginated.
     *
     * @param pageNum Page number (default 0).
     * @param pageSize Number of books per page (default 5).
     * @param searchWord Search term for filtering books.
     * @return A message with the XML file location or error.
     */
    @GetMapping("/xml")
    public String getBooksAsXml(@RequestParam(defaultValue = "0") int pageNum,
                                @RequestParam(defaultValue = "5") int pageSize,
                                @RequestParam String searchWord) {

        try {
            String filePath = bookToXmlService.filterAndSaveBooksToXml(pageNum, pageSize, searchWord, savePath);
            return "XML file saved at: " + filePath + " | Can be changed in application.yml file.";
        } catch (JAXBException | IOException e) {
            return "Error generating XML: " + e.getMessage();
        }
    }

}