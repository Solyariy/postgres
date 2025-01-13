package com.demodb.postgres.tables.services.enums;

import java.util.Arrays;

public enum BookUpdateEnum {
	ALL("all"),
	TITLE("t"),
	PUBLISHER("pub"),
	AUTHOR_ID("aid");
	
	
	
	private final String code;
	
	BookUpdateEnum(String code) {
		this.code = code;
	}
	
	public static BookUpdateEnum fromCode(String code) throws IllegalArgumentException {
		return Arrays.stream(values())
				.filter(type -> type.code.equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid search type: " + code));
	}
}
