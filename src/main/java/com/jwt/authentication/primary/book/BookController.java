package com.jwt.authentication.primary.book;

import com.jwt.authentication.primary.user.UserNotFoundWithIdException;
import com.jwt.authentication.secondary.bookcsv.BookNotFoundWithIsbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/saveBook/{userId}")
    public ResponseEntity<Book> saveBook(@RequestBody Book book,@PathVariable int userId) throws UserNotFoundWithIdException, BookNotFoundWithIsbn {
        Book newBook = bookService.saveBook(book,userId);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }
}