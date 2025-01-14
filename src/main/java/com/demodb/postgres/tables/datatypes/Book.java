package com.demodb.postgres.tables.datatypes;


import com.demodb.postgres.tables.dao.impl.AuthorDaoImpl;
import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String publisher;
    @Getter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @Transient
    private Long authorId;

//    public Book(String isbn, String title, String publisher, Long authorId) {
//        this.isbn = isbn;
//        this.title = title;
//        this.publisher = publisher;
//        this.authorId = authorId;
//    }
//
//    public Book(Long id, String isbn, String title, String publisher, Author author) {
//        this.id = id;
//        this.isbn = isbn;
//        this.title = title;
//        this.publisher = publisher;
//        this.author = author;
//    }
    
    public Book(Long id, String isbn, String title, String publisher, Long authorId) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.publisher = publisher;
        this.authorId = authorId;
    }
}
