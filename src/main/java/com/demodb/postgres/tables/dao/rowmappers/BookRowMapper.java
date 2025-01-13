package com.demodb.postgres.tables.dao.rowmappers;

import com.demodb.postgres.tables.datatypes.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getLong("id"),
                rs.getString("isbn"),
                rs.getString("title"),
                rs.getString("publisher"),
                rs.getLong("author_id"));
    }
}
