package com.demodb.postgres.tables.dao.impl;

import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import com.demodb.postgres.tables.dao.intrface.BookDao;
import com.demodb.postgres.tables.dao.rowmappers.AuthorRowMapper;
import com.demodb.postgres.tables.dao.rowmappers.BookRowMapper;
import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.datatypes.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;
    private final AuthorDao authorDao;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate, final AuthorDao authorDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorDao = authorDao;
    }

    @Override
    public Book create(Book book) throws SQLException {
        return new Book();
    }

    @Override
    public void update(String isbn, String title, String publisher, Long authorId) throws SQLException {
        findViaIsbn(isbn);
        authorDao.findViaId(authorId);
        jdbcTemplate.update(
                "update books set title = ?, publisher = ?, author_id = ? where isbn = ?",
                title, publisher, authorId, isbn
        );
    }

    @Override
    public void delete(String isbn) throws SQLException {
        findViaIsbn(isbn);
        jdbcTemplate.update("delete from books where isbn = ?", isbn);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("select * from books", new BookRowMapper());
    }

    @Override
    public List<Book> findBookViaTitle(String title) throws SQLException {
        List<Book> bookList = jdbcTemplate.query(
                "select * from books where title = ?",
                new BookRowMapper(),
                title
        );
        if (bookList.isEmpty())
            throw new SQLException("Books with title: %s not found".formatted(title));
        return bookList;
    }

    @Override
    public List<Book> findViaIsbn(String isbn) throws SQLException {
        List<Book> bookList = jdbcTemplate.query(
                "select * from books where isbn = ?",
                new BookRowMapper(),
                isbn
        );
        if (bookList.isEmpty()) {
            throw new SQLException("Book with isbn: %s not found".formatted(isbn));
        }
        return bookList;
    }

    @Override
    public List<Book> findViaAuthorId(Long authorId) throws SQLException {
        List<Book> bookList = jdbcTemplate.query(
                "select * from books where author_id = ?",
                new BookRowMapper(),
                authorId
        );
        if (bookList.isEmpty()) {
            throw new SQLException("Books with author_id: %s does not exist".formatted(authorId));
        }
        return bookList;
    }

    @Override
    public List<Book> findViaPublisher(String publisher) throws SQLException {
        List<Book> bookList = jdbcTemplate.query(
                "select * from books where publisher = ?",
                new BookRowMapper(),
                publisher
        );
        if (bookList.isEmpty()) {
            throw new SQLException("Books with publisher: %s does not exist".formatted(publisher));
        }
        return bookList;
    }

    @Override
    public List<Author> findAuthorsViaTitle(String title) throws SQLException {
        return jdbcTemplate.query(
                "select * from authors where id = ?",
                new AuthorRowMapper(),
                findBookViaTitle(title).get(0).getAuthorId()
        );
    }


}
