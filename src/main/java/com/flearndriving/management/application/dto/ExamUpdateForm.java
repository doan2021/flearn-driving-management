package com.flearndriving.management.application.dto;

import java.util.Date;

public class ExamUpdateForm {
    private Long examId;
    private String name;
    private String description;
    private String strDateRegisExamEnd;
    private String strDateExam;
    private Integer status;
    private String typeDrivingLicense;
    private Date dateRegisExamEnd;
    private Date dateExam;

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypeDrivingLicense() {
        return typeDrivingLicense;
    }

    public void setTypeDrivingLicense(String typeDrivingLicense) {
        this.typeDrivingLicense = typeDrivingLicense;
    }

    public String getStrDateRegisExamEnd() {
        return strDateRegisExamEnd;
    }

    public void setStrDateRegisExamEnd(String strDateRegisExamEnd) {
        this.strDateRegisExamEnd = strDateRegisExamEnd;
    }

    public String getStrDateExam() {
        return strDateExam;
    }

    public void setStrDateExam(String strDateExam) {
        this.strDateExam = strDateExam;
    }

    public Date getDateRegisExamEnd() {
        return dateRegisExamEnd;
    }

    public void setDateRegisExamEnd(Date dateRegisExamEnd) {
        this.dateRegisExamEnd = dateRegisExamEnd;
    }

    public Date getDateExam() {
        return dateExam;
    }

    public void setDateExam(Date dateExam) {
        this.dateExam = dateExam;
    }

}
