package com.demodb.postgres.tables.services.handlers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandler {
	
	public ResponseEntity<Object> handleError(Throwable t, Object object) {
		return ResponseEntity
				.badRequest()
				.header("Custom-Header", t.toString())
				.body(object);
	}
	
	public ResponseEntity<Object> handleError(Throwable t) {
		return ResponseEntity
				.badRequest()
				.header("Custom-Header", t.toString())
				.body(null);
	}
	
	
	public ErrorHandler() {}
}
