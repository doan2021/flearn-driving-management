package com.flearndriving.management.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Answer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(columnDefinition = "boolean default false")
	private Boolean isTrue;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = Fields.id)
	private Question question;
}
