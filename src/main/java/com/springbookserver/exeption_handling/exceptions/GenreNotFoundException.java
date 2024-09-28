package com.springbookserver.exeption_handling.exceptions;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Could not find genre with id " + id);
    }
}
