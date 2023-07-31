package com.jwt.authentication.secondary.bookcsv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookCsvService {

    private static final String URL_END = "/jpeg";
    @Autowired
    BookCsvRepository bookCsvRepository;
    public List<BookCsv> getAllBookData() {
        return bookCsvRepository.findAll();
    }

    public BookCsv getBookByISBN(String Isbn) throws BookNotFoundWithIsbn {
        if(bookCsvRepository.findByIsbn(Isbn)==null){
            throw new BookNotFoundWithIsbn("There is no Book with this Isbn number please enter a valid Isbn number");
        }
        return bookCsvRepository.findByIsbn(Isbn);
    }

    public BookCsv saveBookCsvData(BookCsv newBookCsv) throws BookWithThisIsbnPresentException {
        if(bookCsvRepository.findByIsbn(newBookCsv.getIsbn())!=null){
            throw new BookWithThisIsbnPresentException("There is a book already present with this "+newBookCsv.getIsbn()+" number");
        }
       BookCsv bookCsv = BookCsv.builder().
                Isbn(newBookCsv.getIsbn()).
                bookAuthor(newBookCsv.getBookAuthor()).
                bookTitle(newBookCsv.getBookTitle()).
                publisher(newBookCsv.getPublisher()).
                year(newBookCsv.getYear()).
                imageUrlS(UUID.randomUUID()+URL_END).
                imageUrlL(UUID.randomUUID()+URL_END).
                imageUrlM(UUID.randomUUID()+URL_END).
                build();
        return bookCsvRepository.save(bookCsv);
    }
}