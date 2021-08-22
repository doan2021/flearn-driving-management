package com.flearndriving.management.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "question", uniqueConstraints = { @UniqueConstraint(name = "QUESTIONS_UK", columnNames = "number") })
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "number")
    private int number;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_paralysis", columnDefinition = "Boolean default false")
    private boolean isParalysis;

    @Column(name = "is_delete", columnDefinition = "Boolean default false")
    private boolean isDelete;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_at")
    private Date updateAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Document> listImage;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> listAnswers;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @JsonBackReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<ExamQuestionsDetail> listExamQuestionsDetail;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setParalysis(boolean isParalysis) {
        this.isParalysis = isParalysis;
    }

    public List<Document> getListImage() {
        return listImage;
    }

    public void setListImage(List<Document> listImage) {
        this.listImage = listImage;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Boolean isParalysis() {
        return isParalysis;
    }

    public void setParalysis(Boolean isParalysis) {
        this.isParalysis = isParalysis;
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

    public List<Answer> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<Answer> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public List<ExamQuestionsDetail> getListExamQuestionsDetail() {
        return listExamQuestionsDetail;
    }

    public void setListExamQuestionsDetail(List<ExamQuestionsDetail> listExamQuestionsDetail) {
        this.listExamQuestionsDetail = listExamQuestionsDetail;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }
}
