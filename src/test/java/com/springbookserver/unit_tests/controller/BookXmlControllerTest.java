package com.springbookserver.unit_tests.controller;

import com.springbookserver.Application;
import com.springbookserver.config.TestContainerConfigurer;
import com.springbookserver.controller.BookXmlController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.http.ContentType.TEXT;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookXmlControllerTest extends TestContainerConfigurer {

    @Autowired
    private BookXmlController bookXmlController;

    @Test
    void contextLoads() {
        assertThat(bookXmlController).isNotNull();
    }

    @Test
    void shouldSaveXmlAndReturnSuccess() {
        requestSpec
                .contentType(TEXT)
                .body("XML file saved at: C:\\savedXml\\books.xml | Can be changed in application.yml file.")
                .queryParams(Map.of(
                        "pageNum", 0,
                        "pageSize", 2,
                        "searchWord", "game"
                ))
                .when()
                .get("/books/xml")
                .then()
                .statusCode(200);
    }
}
