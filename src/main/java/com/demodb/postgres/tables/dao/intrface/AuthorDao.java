package com.demodb.postgres.tables.dao.intrface;

import com.demodb.postgres.tables.datatypes.Author;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface AuthorDao {
    Author create(Author author) throws SQLException;
    void delete(Long authorId) throws SQLException;
    void update(Long id, List<Object> queryParams) throws SQLException;
    List<Author> getAll() throws SQLException;
    List<Author> findAuthorViaNameAge(String name, Integer age) throws SQLException;
    List<Author> findViaId(Long id) throws SQLException;

}
