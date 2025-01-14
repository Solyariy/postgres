package com.demodb.postgres.tables.dao.impl;

import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import com.demodb.postgres.tables.dao.intrface.BookDao;
import com.demodb.postgres.tables.dao.rowmappers.AuthorRowMapper;
import com.demodb.postgres.tables.dao.rowmappers.BookRowMapper;
import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.datatypes.Book;
import com.demodb.postgres.tables.services.customizers.Str;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;
    private final AuthorDao authorDao;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate, final AuthorDao authorDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorDao = authorDao;
    }

    @Override
    public void create(Book book) throws SQLException {
        authorDao.findViaId(book.getAuthorId());
        jdbcTemplate.update(
                "insert into books(isbn, title, publisher, author_id) values (?,?,?,?)",
                book.getIsbn(), book.getTitle(), book.getPublisher(), book.getAuthorId()
        );
    }
    
    @Override
    public void update(String isbn, List<Str> params) throws SQLException {
        findViaIsbn(isbn);
        int counter = 1;
        StringBuilder query = new StringBuilder("update books set ");
        
        for (Str s: params) {
            query.append(s.getName()).append(" = ?");
            if (counter < params.size()) {
                query.append(", ");
                ++counter;
            }
        }
        query.append(" where isbn = ?");
        String queryFinal = query.toString();
        
        if (params.size() == 3) {
            jdbcTemplate.update(
                    queryFinal,
                    params.get(0).getValue(),
                    params.get(1).getValue(),
                    params.get(2).getValue(),
                    isbn
            );
        } else if (params.size() == 2) {
            jdbcTemplate.update(
                    queryFinal,
                    params.get(0).getValue(),
                    params.get(1).getValue(),
                    isbn
            );
        } else if (params.size() == 1) {
            jdbcTemplate.update(
                    queryFinal,
                    params.get(0).getValue(),
                    isbn
            );
        }
        
    }
    
    
    @Override
    public void delete(String isbn) throws SQLException {
        findViaIsbn(isbn);
        jdbcTemplate.update("delete from books where isbn = ?", isbn);
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("select * from books", new BookRowMapper(authorDao));
    }

    @Override
    public List<Book> findBookViaTitle(String title) throws SQLException {
        List<Book> bookList = jdbcTemplate.query(
                "select * from books where title = ?",
                new BookRowMapper(authorDao),
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
                new BookRowMapper(authorDao),
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
                new BookRowMapper(authorDao),
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
                new BookRowMapper(authorDao),
                publisher
        );
        if (bookList.isEmpty()) {
            throw new SQLException("Books with publisher: %s does not exist".formatted(publisher));
        }
        return bookList;
    }

    @Override
    public List<Author> findAuthorsViaTitle(String title) throws SQLException {
        List<Long> idlist = jdbcTemplate.query(
                "select author_id from books where title = ?",
                ((rs, intRow) -> rs.getLong("author_id")),
                title
        );
        List<Author> authorList = new ArrayList<>();
        for (Long id: idlist) {
            authorList.add(authorDao.findViaId(id).get(0));
        }
        return authorList;
    }


}
