package com.demodb.postgres.tables.datatypes;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private Long id;
	private String name;
	private Integer age;
	
	public Author(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}
