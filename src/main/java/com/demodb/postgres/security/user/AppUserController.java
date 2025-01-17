package com.demodb.postgres.security.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AppUserController {
	
	private final AppUserService appUserService;
	
	@PostMapping("/create")
	public ResponseEntity<java.lang.String> create(@RequestBody AppUser appUser) {
		appUserService.createUser(appUser);
		return ResponseEntity.ok("ok");
	}
	
}
