package com.jwt.authentication.secondary.bookcsv;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_csv_tbl")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCsv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookCsvId;
    @Column(name ="ISBN")
    private String Isbn;
    @Column(name ="Book-Title")
    private String bookTitle;
    @Column(name = "Book-Author")
    private String bookAuthor;
    @Column(name = "Year-Of-Publication")
    private String year;
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "Image-URL-S")
    private String imageUrlS;
    @Column(name = "Image-URL-M")
    private String imageUrlM;
    @Column(name = "Image-URL-L")
    private String imageUrlL;
}