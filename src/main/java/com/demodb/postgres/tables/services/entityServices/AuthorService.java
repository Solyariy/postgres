package com.demodb.postgres.tables.services.entityServices;

import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.services.enums.AuthorSearchEnum;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AuthorService {
	private final AuthorDao authorDao;
	
	public AuthorService(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}
	
	public List<Author> searchAuthor(String searchWay, Long id, String name, Integer age) throws IllegalArgumentException, SQLException {
		AuthorSearchEnum searchType = AuthorSearchEnum.fromCode(searchWay);
		return switch (searchType) {
			case ALL -> authorDao.getAll();
			case NAMEAGE -> authorDao.findAuthorViaNameAge(name, age);
			case ID -> authorDao.findViaId(id);
		};
	}
	
	public void delete(Long id) throws SQLException {
		authorDao.delete(id);
	}
	
	public void create(Author author) throws SQLException {
		authorDao.create(author);
	}
	
	public void update(
			Long id, String nameNew, Integer ageNew
	) throws SQLException {
		List<Object> queryParams;
		if (nameNew == null && ageNew == null) {
			throw new SQLException("No parameters for update");
		} else if (ageNew == null) {
			queryParams = List.of("name = ?", nameNew);
		} else if (nameNew == null) {
			queryParams = List.of("age = ?", ageNew);
		} else {
			queryParams = List.of("name = ?, age = ?", nameNew, ageNew);
		}
		
		authorDao.update(id, queryParams);
	}
}
