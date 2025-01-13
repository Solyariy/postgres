package com.demodb.postgres.tables.services.entityServices;

import com.demodb.postgres.tables.dao.intrface.BookDao;
import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.datatypes.Book;
import com.demodb.postgres.tables.services.customizers.Customizer;
import com.demodb.postgres.tables.services.enums.BookSearchEnum;
import com.demodb.postgres.tables.services.customizers.Str;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
	
	private final BookDao bookDao;
	
	public List<Book> searchBook(String searchWay, String param) throws IllegalArgumentException, SQLException {
		BookSearchEnum searchType = BookSearchEnum.fromCode(searchWay);
		return switch (searchType) {
			case ALL -> bookDao.getAll();
			case ISBN -> bookDao.findViaIsbn(param);
			case TITLE -> bookDao.findBookViaTitle(param);
			case AUTHOR_ID -> bookDao.findViaAuthorId(Long.valueOf(param));
			case PUBLISHER -> bookDao.findViaPublisher(param);
		};
	}
	
	public List<Author> getAuthorViaTitle(String title) throws SQLException {
		return bookDao.findAuthorsViaTitle(Customizer.capitalize(title));
	}
	
	public void delete(String isbn) throws SQLException {
		bookDao.delete(isbn);
	}
	
	
	public void create(Book book) throws SQLException {
		bookDao.create(book);
	}
	
	public void update(String isbn, List<Str> stringList) throws SQLException {
		List<Str> params = new ArrayList<>();
		for (Str s: stringList) {
			if (s.getValue() != null) params.add(s);
		}
		bookDao.update(isbn, params);
		
	}
}
