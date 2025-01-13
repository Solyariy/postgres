package com.demodb.postgres.controllers;

import com.demodb.postgres.tables.dao.intrface.BookDao;
import com.demodb.postgres.tables.datatypes.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookDao.getAll());
    }

    @GetMapping("/get/{wayOfSearch}/{param}")
    public ResponseEntity<List<Book>> get(@PathVariable String wayOfSearch,
                                          @PathVariable Object param) {
        try {
            switch (wayOfSearch) {
                case "t":
                    return ResponseEntity.ok(bookDao.findBookViaTitle(param.toString()));
                case "i":
                    return ResponseEntity.ok(bookDao.findViaIsbn(param.toString()));
                case "a":
                    return ResponseEntity.ok(bookDao.findViaAuthorId(Long.valueOf(param.toString())));
                case "p":
                    return ResponseEntity.ok(bookDao.findViaPublisher(param.toString()));
            }
        } catch (SQLException e) {
            return ResponseEntity
                    .badRequest()
                    .header("Custom-Header", e.toString())
                    .body(List.of());
        }
        return ResponseEntity
                .badRequest()
                .body(List.of());
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<String> delete(@PathVariable String isbn) {
        try {
            bookDao.delete(isbn);
            return ResponseEntity.ok("ISBN: %s deleted".formatted(isbn));
        } catch (SQLException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.toString());
        }
    }

    @PostMapping("/create/{isbn}/{title}/{publisher}/{authorId}")
    public ResponseEntity<Book> create(@PathVariable String isbn,
                                       @PathVariable String title,
                                       @PathVariable String publisher,
                                       @PathVariable Long authorId) {
        Book bookNew = new Book(isbn, title, publisher, authorId);
        try {
            bookDao.create(bookNew);
            return ResponseEntity.ok(bookNew);
        } catch (SQLException e) {
            return ResponseEntity
                    .badRequest()
                    .header("Custom-Header", e.toString())
                    .body(bookNew);
        }

    }

    @PutMapping("/update/{isbn}/{titleNew}/{pubNew}/{authorIdNew}")
    public ResponseEntity<String> update(@PathVariable String isbn,
                                         @PathVariable String titleNew,
                                         @PathVariable String pubNew,
                                         @PathVariable Long authorIdNew) {
        try {
            bookDao.update(isbn, titleNew, pubNew, authorIdNew);
            return ResponseEntity.ok("Ok");
        } catch (SQLException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.toString());
        }
    }
}
