package com.springbookserver.exeption_handling.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Could not find user with id " + id);
    }
}
