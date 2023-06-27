package com.flearndriving.management.application.entities;

import java.util.Date;

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
public class HistoryAnswer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String note;

	@Column
	private Date dateAnswer;

	@Column
	private Boolean isCorrect;

	@ManyToOne
	@JoinColumn(name = "answer_id", referencedColumnName = Fields.id)
	private Answer answer;
}
