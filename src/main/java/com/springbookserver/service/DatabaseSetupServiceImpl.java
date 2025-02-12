package com.springbookserver.service;

import com.springbookserver.utils.DatabaseInsertionHelper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseSetupServiceImpl {

    private final DatabaseInsertionHelper databaseInsertionHelper;

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("Books, authors and genres are connected!");
        databaseInsertionHelper.connectBooksAndAuthorsAndGenres();
    }
}
