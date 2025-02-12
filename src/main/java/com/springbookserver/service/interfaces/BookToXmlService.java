package com.springbookserver.service.interfaces;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface BookToXmlService {
    String filterAndSaveBooksToXml(int pageNum, int pageSize, String searchWord, String savePath) throws IOException, JAXBException;
}
