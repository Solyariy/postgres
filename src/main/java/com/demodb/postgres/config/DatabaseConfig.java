package com.demodb.postgres.config;

import com.demodb.postgres.tables.dao.impl.AuthorDaoImpl;
import com.demodb.postgres.tables.dao.impl.BookDaoImpl;
//import com.demodb.postgres.tables.dao.impl.UserDaoImpl;
import com.demodb.postgres.tables.dao.intrface.AuthorDao;
import com.demodb.postgres.tables.dao.intrface.BookDao;
//import com.demodb.postgres.tables.dao.intrface.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
	@Bean
	public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public AuthorDao authorDao(JdbcTemplate jdbcTemplate) {
		return new AuthorDaoImpl(jdbcTemplate);
	}
	
	@Bean
	public BookDao bookDao(JdbcTemplate jdbcTemplate, AuthorDao authorDao) {
		return new BookDaoImpl(jdbcTemplate, authorDao);
	}
//
//	@Bean
//	public UserDao userDao(JdbcTemplate jdbcTemplate) {
//		return new UserDaoImpl(jdbcTemplate);
//	}
//
}
