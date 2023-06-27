package com.flearndriving.management.application.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Question extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private Integer number;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(columnDefinition = "Boolean default false")
	private Boolean isParalysis;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Document> listImage;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Answer> listAnswers;

	@ManyToOne
	@JoinColumn(name = "chapter_id", referencedColumnName = Fields.id)
	private Chapter chapter;
}
