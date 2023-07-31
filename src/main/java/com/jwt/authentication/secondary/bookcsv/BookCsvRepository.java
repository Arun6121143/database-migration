package com.jwt.authentication.secondary.bookcsv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCsvRepository extends JpaRepository<BookCsv,Integer> {
    @Query("SELECT b FROM BookCsv b WHERE b.Isbn= :Isbn")
    BookCsv findByIsbn(String Isbn);
}