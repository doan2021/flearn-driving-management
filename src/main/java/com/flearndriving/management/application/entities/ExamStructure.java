package com.flearndriving.management.application.entities;

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
public class ExamStructure extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = Fields.id)
    private Exam Exam;
}
