package com.demodb.postgres.tables.datatypes;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String isbn;
	private String title;
	private String publisher;
	@Getter
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	@Transient
	private Long authorId;
	
}
