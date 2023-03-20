package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class HistoryAnswer extends AbstractEntity {

    @Column
    private String note;

    @Column
    private Date dateAnswer;

    @Column
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "trial_exam_result_id", referencedColumnName = "id")
    private TrialExamResult trialExamResult;

    @ManyToOne
    @JoinColumn(name = "exam_profile_id", referencedColumnName = "id")
    private ExamProfile examProfile;

    @ManyToOne
    @JoinColumn(name = "status_learn_id", referencedColumnName = "id")
    private StatusLearn statusLearn;
}
