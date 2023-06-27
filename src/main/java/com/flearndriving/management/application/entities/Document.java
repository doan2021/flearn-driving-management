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
public class Document extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String fileName;

	@Column
	private String originFileName;

	@Column
	private Long size;

	@Column
	private String extension;

	@Column
	private String contentType;

	@Column
	private String path;

	@Column
	private Integer type;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = Fields.id)
	private Question question;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = Fields.id)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "chapter_id", referencedColumnName = Fields.id)
	private Chapter chapter;
}
