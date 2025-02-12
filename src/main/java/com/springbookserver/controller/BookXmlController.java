package com.springbookserver.controller;

import com.springbookserver.service.interfaces.BookToXmlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;

@RestController
@RequestMapping("/books")
public class BookXmlController {

    @Value("${path.xml.save}")
    private String savePath;

    private final BookToXmlService bookToXmlService;

    public BookXmlController(BookToXmlService bookToXmlService) {
        this.bookToXmlService = bookToXmlService;
    }

    @GetMapping("/xml")
    public String getBooksAsXml(@RequestParam(defaultValue = "0") int pageNum,
                                @RequestParam(defaultValue = "5") int pageSize,
                                @RequestParam(required = false) String searchWord) {

        try {
            String filePath = bookToXmlService.filterAndSaveBooksToXml(pageNum, pageSize, searchWord, savePath);
            return "XML file saved at: " + filePath + " | Can be changed in application.yml file.";
        } catch (JAXBException | IOException e) {
            return "Error generating XML: " + e.getMessage();
        }
    }

}