package com.demodb.postgres.tables.services.customizers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Str {
	private Object value;
	private String name;
//
//	public Object getValue() {
//		if (name.equals("author_id")) return Long.valueOf(value);
//	}
}
