package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class TrialExamResult extends AbstractEntity {

    @Column
    private double point;

    @Column
    private boolean isPass;

    @Column
    private Integer numberCorrect;

    @Column
    private Integer numberIncorrect;

    @Column
    private Integer timeExam;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "trialExamResult", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoryAnswer> listHistoryAnswer;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "exam_questions_id", referencedColumnName = "id")
    private ExamQuestions examQuestions;

}
