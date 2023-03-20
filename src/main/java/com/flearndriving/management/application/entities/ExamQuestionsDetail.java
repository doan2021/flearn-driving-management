package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ExamQuestionsDetail extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "exam_questions_id", referencedColumnName = "id")
    private ExamQuestions examQuestions;

}
