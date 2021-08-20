package com.flearndriving.management.application.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "history_answer")
public class HistoryAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_answer_id")
    private Long historyAnswerId;

    @Column(name = "note")
    private String note;

    @Column(name = "date_answer")
    private Date dateAnswer;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "trial_exam_result_id")
    private TrialExamResult trialExamResult;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "exam_profile_id")
    private ExamProfile examProfile;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "status_learn_id")
    private StatusLearn statusLearn;

    public Long getHistoryAnswerId() {
        return historyAnswerId;
    }

    public void setHistoryAnswerId(Long historyAnswerId) {
        this.historyAnswerId = historyAnswerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Date dateAnswer) {
        this.dateAnswer = dateAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public TrialExamResult getTrialExamResult() {
        return trialExamResult;
    }

    public void setTrialExamResult(TrialExamResult trialExamResult) {
        this.trialExamResult = trialExamResult;
    }

    public ExamProfile getExamProfile() {
        return examProfile;
    }

    public void setExamProfile(ExamProfile examProfile) {
        this.examProfile = examProfile;
    }

    public StatusLearn getStatusLearn() {
        return statusLearn;
    }

    public void setStatusLearn(StatusLearn statusLearn) {
        this.statusLearn = statusLearn;
    }

}
