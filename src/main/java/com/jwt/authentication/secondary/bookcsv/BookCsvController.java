package com.jwt.authentication.secondary.bookcsv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookCsvController {

    @Autowired
    BookCsvService bookCsvService;

    @PostMapping("/saveBookData")
    public ResponseEntity<BookCsv> saveBookCsvData(@RequestBody BookCsv bookCsv) throws BookWithThisIsbnPresentException {
        BookCsv newBookCsv = bookCsvService.saveBookCsvData(bookCsv);
        return new ResponseEntity<>(newBookCsv,HttpStatus.OK);
    }
    @GetMapping("/getAllBookData")
    public ResponseEntity<List<BookCsv>> getAllBookCsv(){
        List<BookCsv> BookCsvList = bookCsvService.getAllBookData();
        return new ResponseEntity<>(BookCsvList, HttpStatus.OK);
    }

    @GetMapping("/findBook/{Isbn}")
    public ResponseEntity<BookCsv> findBookByISBN(@PathVariable String Isbn) throws BookNotFoundWithIsbn {
        BookCsv existingBookData = bookCsvService.getBookByISBN(Isbn);
        return new ResponseEntity<>(existingBookData,HttpStatus.OK);
    }
}