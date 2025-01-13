package com.demodb.postgres.tables.dao.rowmappers;

import com.demodb.postgres.tables.datatypes.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("age"));
    }
}
