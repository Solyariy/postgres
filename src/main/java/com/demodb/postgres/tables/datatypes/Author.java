package com.demodb.postgres.tables.datatypes;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {
    private Long id;
    private String name;
    private Integer age;

    public Author(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
