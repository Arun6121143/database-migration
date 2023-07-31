package com.jwt.authentication;

import com.jwt.authentication.secondary.bookcsv.BookCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TransferData  implements ApplicationRunner {
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbc;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbc;

    public void transferData() {
        List<BookCsv> masterData = secondaryJdbc.query("SELECT * FROM book_csv_tbl", new BeanPropertyRowMapper<>(BookCsv.class));
        masterData.stream().forEach(row -> {
            primaryJdbc.update("INSERT INTO book_csv2_tbl (ISBN,`Book-Title`,`Year-Of-Publication`,`Publisher`,`Image-URL-S`,`Image-URL-M`,`Image-URL-L`) " +
                    "VALUES (?,?,?,?,?,?,?)", row.getIsbn(), row.getBookTitle(), row.getYear(), row.getPublisher(), row.getImageUrlS(), row.getImageUrlM(), row.getImageUrlL());
        });
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        transferData();
    }
}