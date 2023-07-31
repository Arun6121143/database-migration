package com.jwt.authentication.secondary.bookcsv;

public class BookWithThisIsbnPresentException extends Exception {
    public BookWithThisIsbnPresentException(String message) {
        super(message);
    }
}