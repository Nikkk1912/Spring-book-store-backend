package com.springbookserver.controller;

import com.springbookserver.utils.DatabaseInsertionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("utils")
@RequiredArgsConstructor
public class UtilController {

    private final DatabaseInsertionHelper databaseInsertionHelper;

    // POST
    @PostMapping("/connectBooks")
    public ResponseEntity<String> connectBooks() {
        databaseInsertionHelper.connectBooksAndAuthorsAndGenres();
        return ResponseEntity.ok("Books, authors and genres connected");
    }
}
