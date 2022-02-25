package com.api_test.book.exception;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUDI = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
