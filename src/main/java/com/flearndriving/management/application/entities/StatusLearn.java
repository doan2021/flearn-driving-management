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
public class StatusLearn extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private Integer correctNumberOfTimes;

	@Column
	private Integer incorrectNumberOfTimes;

	@Column
	private int statusQuestion;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = Fields.id)
	private Question question;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = Fields.id)
	private Customer customer;

	@OneToMany(mappedBy = "statusLearn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<HistoryAnswer> listHistoryAnswer;
}
