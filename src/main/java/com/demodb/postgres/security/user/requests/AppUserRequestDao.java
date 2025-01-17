package com.demodb.postgres.security.user.requests;

import com.demodb.postgres.security.user.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AppUserRequestDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public void create(AppUser appUser) {
		jdbcTemplate.update(
				"insert into app_user(email, first_name, last_name, password) values (?,?,?,?)",
				appUser.getEmail(), appUser.getFirstName(), appUser.getLastName(), appUser.getPassword()
		);
	}
}
