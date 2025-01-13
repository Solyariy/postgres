package com.demodb.postgres.controllers;

import com.demodb.postgres.tables.datatypes.Author;
import com.demodb.postgres.tables.services.entityServices.AuthorService;
import com.demodb.postgres.tables.services.handlers.ErrorHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {
	
	private final AuthorService authorService;
	private final ErrorHandler errorHandler;
	
	public AuthorController(AuthorService authorService, ErrorHandler errorHandler) {
		this.authorService = authorService;
		this.errorHandler = errorHandler;
	}
	
	@GetMapping("/get/{wayOfSearch}")
	public ResponseEntity<Object> get(@PathVariable String wayOfSearch,
	                                  @RequestParam(required = false) Long id,
	                                  @RequestParam(required = false) String name,
	                                  @RequestParam(required = false) Integer age) {
		try {
			List<Author> authorList = authorService.searchAuthor(wayOfSearch, id, name, age);
			return ResponseEntity.ok(authorList);
		} catch (SQLException | IllegalArgumentException e) {
			return errorHandler.handleError(e);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		try {
			authorService.delete(id);
			return ResponseEntity.ok("Ok");
		} catch (SQLException e) {
			return errorHandler.handleError(e);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestParam Integer age,
	                                     @RequestParam String name) {
		Author author = new Author(name, age);
		try {
			authorService.create(author);
			return ResponseEntity.ok(author);
		} catch (SQLException e) {
			return errorHandler.handleError(e);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam Long id,
	                                     @RequestParam(required = false) Integer ageNew,
	                                     @RequestParam(required = false) String nameNew) {
		try {
			authorService.update(id, nameNew, ageNew);
			return ResponseEntity.ok("Ok");
		} catch (SQLException e) {
			return errorHandler.handleError(e);
		}
	}
}
