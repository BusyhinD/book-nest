package com.book.nest.exception;

public class DataProcessionException extends RuntimeException {
    public DataProcessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
