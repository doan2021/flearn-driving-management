package com.flearndriving.management.application.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "exam_questions_detail")
public class ExamQuestionsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_questions_detail_id")
    private Long examQuestionsDetailId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_questions_id")
    private ExamQuestions examQuestions;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_at")
    private Date updateAt;

    public Long getExamQuestionsDetailId() {
        return examQuestionsDetailId;
    }

    public void setExamQuestionsDetailId(Long examQuestionsDetailId) {
        this.examQuestionsDetailId = examQuestionsDetailId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ExamQuestions getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(ExamQuestions examQuestions) {
        this.examQuestions = examQuestions;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

}
