package com.demodb.postgres.security.user.requests;

import com.demodb.postgres.security.user.AppUser;
import com.demodb.postgres.tables.services.customizers.Customizer;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserRequestService {
	
	private final AppUserRequestDao requestDao;
	private final BCryptPasswordEncoder encoder;
	
	public void create(AppUser appUser) {
		appUser.setEmail(appUser.getEmail().toLowerCase());
		appUser.setFirstName(Customizer.capitalize(appUser.getFirstName()));
		appUser.setLastName(Customizer.capitalize(appUser.getLastName()));
		appUser.setPassword(encoder.encode(appUser.getPassword()));
		requestDao.create(appUser);
	}
}
