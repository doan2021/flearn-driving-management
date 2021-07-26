package com.doanfpt.management.application.dto;

import java.util.List;

public class ExamQuestionsForm {

    private String name;
    private String description;
    private Long drivingLicenseId;
    private List<Long> listIdQuestion;

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

    public List<Long> getListIdQuestion() {
        return listIdQuestion;
    }

    public void setListIdQuestion(List<Long> listIdQuestion) {
        this.listIdQuestion = listIdQuestion;
    }

    public Long getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(Long drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

}
