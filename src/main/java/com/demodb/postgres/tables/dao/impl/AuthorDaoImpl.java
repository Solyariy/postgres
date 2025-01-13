package com.demodb.postgres.tables.dao.impl;

import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import com.demodb.postgres.tables.dao.rowmappers.AuthorRowMapper;
import com.demodb.postgres.tables.datatypes.Author;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author create(Author author) throws SQLException {
        
        if (!findAuthorViaNameAge(author.getName(), author.getAge()).isEmpty()) {
            throw new SQLException(
                    "Author with name: %s and age: %s already exists"
                            .formatted(author.getName(), author.getAge()));
        }
        jdbcTemplate.update(
                "insert into authors(name, age) values (?,?);",
                author.getName(), author.getAge()
        );
        return author;
    }

    @Override
    public void delete(Long authorId) throws SQLException {
        if (findViaId(authorId).isEmpty()) {
            throw new SQLException("No object to delete with id: " + authorId);
        }
        jdbcTemplate.update(
                "delete from authors where id = ?;",
                authorId
        );
    }

    @Override
    public void update(Long id, List<Object> queryParams) throws SQLException {
        if (findViaId(id).isEmpty()) {
            throw new SQLException("Object with id: %s, not found".formatted(id));
        }
        String query = "update authors set " + queryParams.get(0) + " where id = ?";
        if (queryParams.size() == 3) {
            jdbcTemplate.update(query, queryParams.get(1), queryParams.get(2), id);
        } else {
            jdbcTemplate.update(query, queryParams.get(1), id);
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbcTemplate.query("select * from authors", new AuthorRowMapper());
    }

    @Override
    public List<Author> findAuthorViaNameAge(String name, Integer age) throws SQLException {
	    //        if (authorList.isEmpty())
//            throw new SQLException("Author with name: %s and age: %s not found".formatted(name, age));
        return jdbcTemplate.query(
                "select * from authors where name = ? and age = ?",
                new AuthorRowMapper(),
                name, age
        );
    }

    @Override
    public List<Author> findViaId(Long id) throws SQLException {
        List<Author> authorList = jdbcTemplate.query(
                "select * from authors where id = ?",
                new AuthorRowMapper(),
                id
        );
        if (authorList.isEmpty())
            throw new SQLException("Author with id: %s not found".formatted(id));
        return authorList;
    }

}
