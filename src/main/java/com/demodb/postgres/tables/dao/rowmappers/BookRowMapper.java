package com.demodb.postgres.tables.dao.rowmappers;

import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.datatypes.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class BookRowMapper implements RowMapper<Book> {
	
	private final AuthorDao authorDao;
	
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Book(
				rs.getLong("id"),
				rs.getString("isbn"),
				rs.getString("title"),
				rs.getString("publisher"),
				authorDao.findViaId(rs.getLong("author_id")).get(0),
				rs.getLong("author_id")
		);
	}
}
