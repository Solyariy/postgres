package com.demodb.postgres.tables.services.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BookSearchEnum {
	ALL("all"),
	ISBN("isbn"),
	TITLE("title"),
	AUTHOR_ID("aid"),
	PUBLISHER("pub");
	
	
	private final String code;
	
	BookSearchEnum(String code) {
		this.code = code;
	}
	
	public static BookSearchEnum fromCode(String code) throws IllegalArgumentException {
		return Arrays.stream(values())
				.filter(type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid search type: " + code));
	}
}
