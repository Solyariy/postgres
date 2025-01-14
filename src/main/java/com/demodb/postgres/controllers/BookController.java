package com.demodb.postgres.controllers;

import com.demodb.postgres.tables.datatypes.Book;
import com.demodb.postgres.tables.services.entityServices.BookService;
import com.demodb.postgres.tables.services.handlers.ErrorHandler;
import com.demodb.postgres.tables.services.customizers.Str;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
@AllArgsConstructor
public class BookController {
	private final BookService bookService;
	private final ErrorHandler errorHandler;
	
	@GetMapping("/get/{wayOfSearch}")
	public ResponseEntity<Object> get(@PathVariable String wayOfSearch,
	                                  @RequestParam(required = false) String param) {
		try {
			if (param != null && wayOfSearch.equals("authors")){
				return ResponseEntity.ok(bookService.getAuthorViaTitle(param));
			}
			return ResponseEntity.ok(bookService.searchBook(wayOfSearch, param));
		} catch (SQLException e) {
			return errorHandler.handleError(e);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestParam String isbn) {
		try {
			bookService.delete(isbn);
			return ResponseEntity.ok("ISBN: %s deleted".formatted(isbn));
		} catch (SQLException e) {
			return errorHandler.handleError(e);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody Book book) {
		try {
			bookService.create(book);
			return ResponseEntity.ok(book);
		} catch (SQLException e) {
			return errorHandler.handleError(e, book);
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestParam(required = false) String isbn,
	                                     @RequestParam(required = false) String title,
	                                     @RequestParam(required = false) String pub,
	                                     @RequestParam(required = false) Long aid) {
		try {
			bookService.update(isbn, List.of(
					new Str(title, "title"),
					new Str(pub, "publisher"),
					new Str(aid, "author_id")
			));
			return ResponseEntity.ok("Ok");
		} catch (SQLException | IllegalArgumentException e) {
			return errorHandler.handleError(e);
		}
	}
}
