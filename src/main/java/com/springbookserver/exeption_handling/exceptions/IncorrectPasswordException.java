package com.springbookserver.exeption_handling.exceptions;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Incorrect password. Try again.");
    }
}
