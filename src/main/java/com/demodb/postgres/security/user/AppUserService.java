package com.demodb.postgres.security.user;

import com.demodb.postgres.security.user.requests.AppUserRequestService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
	
	private final AppUserDao appUserDao;
	private final AppUserRequestService requestService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserDao.findByEmail(email);
	}
	
	public void createUser(AppUser appUser) {
		requestService.create(appUser);
	}
}
