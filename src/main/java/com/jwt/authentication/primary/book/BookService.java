package com.jwt.authentication.primary.book;

import com.jwt.authentication.primary.user.User;
import com.jwt.authentication.primary.user.UserNotFoundWithIdException;
import com.jwt.authentication.primary.user.UserRepository;
import com.jwt.authentication.secondary.bookcsv.BookCsv;
import com.jwt.authentication.secondary.bookcsv.BookCsvRepository;
import com.jwt.authentication.secondary.bookcsv.BookNotFoundWithIsbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCsvRepository bookCsvRepository;

    public Book saveBook(Book book, int userId) throws UserNotFoundWithIdException, BookNotFoundWithIsbn {
        User existingUser = userRepository.findById(userId);
        BookCsv existingBookCsv = bookCsvRepository.findByIsbn(book.getBookIsbnNumber());
        if (existingBookCsv == null) {
            throw new BookNotFoundWithIsbn("Book with this isbn is not present in table first add the book and then allot to the user");
        }
        if (existingUser == null) {
            throw new UserNotFoundWithIdException("user with this Id not registered or present hence cannot assign a book to him");
        }
        book.setUser(existingUser);
        Book newBook = bookRepository.save(book);
        existingUser.getUserBookList().add(newBook);
        userRepository.save(existingUser);
        return newBook;
    }
}