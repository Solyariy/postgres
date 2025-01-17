package com.demodb.postgres.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/")
	public ResponseEntity<java.lang.String> m() {
		return ResponseEntity.ok("Main page");
	}
	
	@GetMapping("/login")
	public ResponseEntity<java.lang.String> login() {
		return ResponseEntity.ok("logged in");
	}
	
	@GetMapping("/logout")
	public ResponseEntity<java.lang.String> logout() {
		return ResponseEntity.ok("logged out");
	}
	
}
