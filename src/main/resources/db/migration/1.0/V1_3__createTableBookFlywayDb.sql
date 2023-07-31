CREATE TABLE book_tbl(
BookId bigint(20) NOT NULL AUTO_INCREMENT,
bookName VARCHAR(20) NOT NULL,
authorName VARCHAR(20) NOT NULL,
userId bigint(20) NOT NULL,
PRIMARY KEY(bookId),
FOREIGN KEY(userId) REFERENCES flyway_db.user_tbl(id)
);