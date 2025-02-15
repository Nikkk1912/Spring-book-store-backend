package com.springbookserver.unit_tests.service;

import com.springbookserver.Application;
import com.springbookserver.service.interfaces.BookToXmlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookToXmlServiceTest {

    @Autowired
    private BookToXmlService bookXmlService;

    @Test
    @Transactional
    public void shouldGenerateXmlForValidBook() throws Exception {
        String savePath = "target/test-xml";

        String resultPath = bookXmlService.filterAndSaveBooksToXml(0, 2, "Book", savePath);

        Path resultFilePath = Path.of(resultPath);
        assertTrue(Files.exists(resultFilePath), "XML file was not created");

        String xmlContent = Files.readString(resultFilePath);
        assertTrue(xmlContent.contains("<books>"), "XML content does not contain expected books element");
        assertTrue(xmlContent.contains("<book>"), "XML content does not contain expected book element");
        assertTrue(xmlContent.contains("<author>"), "XML content does not contain expected author element");
        assertTrue(xmlContent.contains("<genre>"), "XML content does not contain expected genre element");

        Files.delete(resultFilePath);
    }

    @Test
    @Transactional
    public void shouldGenerateEmptyXmlWhenBadSearchWord() throws Exception {
        String savePath = "target/test-xml";

        //Random search word
        String resultPath = bookXmlService.filterAndSaveBooksToXml(0, 2, "veryRandomWordsAndDefinitelyNotBookTitle", savePath);

        Path resultFilePath = Path.of(resultPath);
        assertTrue(Files.exists(resultFilePath), "XML file was not created");

        String xmlContent = Files.readString(resultFilePath);
        assertTrue(xmlContent.contains("<books/>"), "XML content does not contain expected books element");

        Files.delete(resultFilePath);
    }
}
