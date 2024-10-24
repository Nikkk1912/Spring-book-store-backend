package com.springbookserver.exeption_handling.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(Long id) {
        super("Could not find cart with id " + id);
    }
}
