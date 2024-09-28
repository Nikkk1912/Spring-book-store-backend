package com.springbookserver.exeption_handling.exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super("Could not find author with id " + id);
    }
}
