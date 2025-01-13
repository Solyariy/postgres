package com.demodb.postgres.tables.dao.intrface;

import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.datatypes.Book;
import com.demodb.postgres.tables.services.customizers.Str;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface BookDao {
    void create(Book book) throws SQLException;
    void update(String isbn, List<Str> params) throws SQLException;
    void delete(String isbn) throws SQLException;
    List<Book> getAll();
    List<Book> findBookViaTitle(String title) throws SQLException;
    List<Book> findViaIsbn(String isbn) throws SQLException;
    List<Book> findViaAuthorId(Long authorId) throws SQLException;
    List<Book> findViaPublisher(String publisher) throws SQLException;
    List<Author> findAuthorsViaTitle(String title) throws SQLException;
}
