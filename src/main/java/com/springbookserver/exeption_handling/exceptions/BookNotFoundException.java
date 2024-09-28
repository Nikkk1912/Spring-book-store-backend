package com.springbookserver.exeption_handling.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Could not find book with id " + id);
    }
}
