package org.neocities.aletheos.Library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "articles")
@Data public class Article {
	@Id
	private Long isbn;
	private String title;
	private String author;
	private String classification;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private ArticleType type;
}
