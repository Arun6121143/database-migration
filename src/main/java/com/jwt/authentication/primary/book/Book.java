package com.jwt.authentication.primary.book;

import com.jwt.authentication.primary.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "book_tbl")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @Column(name = "BookIsbn")
    private String bookIsbnNumber;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}