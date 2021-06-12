package com.doanfpt.management.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "name")
    private String name;

    @Column(name = "is_trial")
    public boolean isTrial;

    @Column(name = "date_exam")
    private Date date_exam;
    
    @Column(name = "description")
    private String description;
    
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

    @JsonManagedReference
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamResult> listExamResult;

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isTrial() {
        return isTrial;
    }

    public void setTrial(boolean isTrial) {
        this.isTrial = isTrial;
    }

    public Date getDate_exam() {
        return date_exam;
    }

    public void setDate_exam(Date date_exam) {
        this.date_exam = date_exam;
    }

    public List<ExamResult> getListExamResult() {
        return listExamResult;
    }

    public void setListExamResult(List<ExamResult> listExamResult) {
        this.listExamResult = listExamResult;
    }

}
