package com.demodb.postgres.tables.services.enums;

import java.util.Arrays;

public enum AuthorSearchEnum {
    ALL("all"),
    NAMEAGE("na"),
    ID("id");

    private final String code;

    AuthorSearchEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static AuthorSearchEnum fromCode(String code) throws IllegalArgumentException {
        return Arrays.stream(values())
                .filter(type -> type.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid search type: " + code));
    }

}
