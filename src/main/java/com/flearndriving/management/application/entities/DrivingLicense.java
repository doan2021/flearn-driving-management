package com.flearndriving.management.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class DrivingLicense extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;

	@Column
	private Double price;

	@Column
	private Integer numberQuestion;

	@Column
	private Integer numberQuestionParalysis;

	@Column
	private Integer numberQuestionCorrect;

	@Column
	private Integer examMinutes;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column
	private Integer numberYearExpires;
}
