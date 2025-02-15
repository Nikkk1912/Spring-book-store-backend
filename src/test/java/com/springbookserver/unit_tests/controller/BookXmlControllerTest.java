package com.springbookserver.unit_tests.controller;

import com.springbookserver.Application;
import com.springbookserver.config.PostgreSQLExtension;
import com.springbookserver.config.RestAssuredConfigurer;
import com.springbookserver.controller.BookXmlController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.http.ContentType.TEXT;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({PostgreSQLExtension.class})
class BookXmlControllerTest extends RestAssuredConfigurer {

    @Autowired
    private BookXmlController bookXmlController;

    @Test
    @Order(1)
    void contextLoads() {
        assertThat(bookXmlController).isNotNull();
    }

    @Test
    @Order(2)
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

    @Test
    @Order(3)
    void shouldReturnBadRequestWithMissingArgument() {
        requestSpec
                .contentType(JSON)
                .queryParams(Map.of(
                        "pageNum", 0,
                        "pageSize", 2
                ))
                .when()
                .get("/books/xml")
                .then()
                .statusCode(400)
                .body("error", equalTo("Bad Request"))
                .body("path", equalTo("/books/xml"));
    }
}