package com.demodb.postgres.tables.datatypes;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long bookId;
    private String isbn;
    private String title;
    private String publisher;
    private Long authorId;

    public Book(String isbn, String title, String publisher, Long authorId) {
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.authorId = authorId;
    }

}
