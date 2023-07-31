package com.jwt.authentication.secondary.bookcsv;

public class BookNotFoundWithIsbn extends Exception{
    public BookNotFoundWithIsbn(String message) {
        super(message);
    }
}