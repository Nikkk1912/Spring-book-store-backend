package com.springbookserver.service.interfaces;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

/**
 * Interface for services that convert books to XML format and save them to a file.
 */
public interface BookToXmlService {
    /**
     * Filters books based on search criteria, converts them to XML, and saves them to a file.
     *
     * @param pageNum The page number for pagination.
     * @param pageSize The number of books per page.
     * @param searchWord The search term for filtering books.
     * @param savePath The path to save the generated XML file.
     * @return The path to the saved XML file.
     * @throws IOException If an error occurs while saving the file.
     * @throws JAXBException If an error occurs during XML conversion.
     */
    String filterAndSaveBooksToXml(int pageNum, int pageSize, String searchWord, String savePath) throws IOException, JAXBException;
}
