package com.doanfpt.management.application.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_questions")
public class ExamQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_questions_id")
    private Long examQuestionsId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "list_id_question")
    private String listIdQuestion;
    
    @Column(name = "is_delete", columnDefinition = "boolean default false")
    private boolean isDelete;
    
    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_at")
    private Date updateAt;

    public Long getExamQuestionsId() {
        return examQuestionsId;
    }

    public void setExamQuestionsId(Long examQuestionsId) {
        this.examQuestionsId = examQuestionsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getListIdQuestion() {
        return listIdQuestion;
    }

    public void setListIdQuestion(String listIdQuestion) {
        this.listIdQuestion = listIdQuestion;
    }

}
