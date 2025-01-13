package com.demodb.postgres.tables.services.customizers;

public class Customizer {
	static public String capitalize(String line) {
		StringBuilder sb = new StringBuilder();
		char previousChar = ' ';
		for (char c : line.toCharArray()) {
			if (previousChar == ' ' && c == ' ') {}
			else if (previousChar == ' ') {
				c = Character.toUpperCase(c);
				sb.append(c);
				previousChar = c;
			} else {
				c = Character.toLowerCase(c);
				sb.append(c);
				previousChar = c;
			}
		}
		return sb.toString();
	}
}
