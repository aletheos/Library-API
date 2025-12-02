package org.neocities.aletheos.Library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "types")
@Data public class ArticleType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String type;
	@OneToMany(mappedBy = "type")
	private Set<Article> articles;
}
