package com.flearndriving.management.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Chapter extends AbstractEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;

	@Column
	private String content;

	@Column(columnDefinition = "TEXT")
	private String description;

	@OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Question> listQuestion;

	@OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Document> listImages;
}
