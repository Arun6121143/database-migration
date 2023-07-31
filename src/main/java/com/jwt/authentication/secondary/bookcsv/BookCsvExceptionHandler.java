package com.jwt.authentication.secondary.bookcsv;

import com.jwt.authentication.primary.user.MapErrorMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookCsvExceptionHandler implements MapErrorMessage {

    @ExceptionHandler(BookNotFoundWithIsbn.class)
    public Map<String,String> BookNotWithIsbnExceptionHandler(BookNotFoundWithIsbn bookNotFoundWithIsbn){
        return MapErrorMessage(bookNotFoundWithIsbn);
    }

    @ExceptionHandler(BookWithThisIsbnPresentException.class)
    public Map<String,String> BookWithThisIsbnPresentExceptionHandler(BookWithThisIsbnPresentException bookWithThisIsbnPresentException){
        return MapErrorMessage(bookWithThisIsbnPresentException);
    }
    @Override
    public Map<String, String> MapErrorMessage(Exception exception) {
        Map<String,String> erroMap = new HashMap<>();
        erroMap.put("error message", exception.getMessage());
        return erroMap;
    }
}