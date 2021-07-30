package com.doanfpt.management.application.dto;

public class DrivingLicenseForm {

    private String name;
    private Double price;
    private Integer numberQuestion;
    private Integer numberQuestionCorect;
    private Integer numberQuestionParalysis;
    private String description;
    private Integer examMinutes;
    private Integer numberYearExpires;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public Integer getExamMinutes() {
        return examMinutes;
    }

    public void setExamMinutes(Integer examMinutes) {
        this.examMinutes = examMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberYearExpires() {
        return numberYearExpires;
    }

    public void setNumberYearExpires(Integer numberYearExpires) {
        this.numberYearExpires = numberYearExpires;
    }

    public Integer getNumberQuestionCorect() {
        return numberQuestionCorect;
    }

    public void setNumberQuestionCorect(Integer numberQuestionCorect) {
        this.numberQuestionCorect = numberQuestionCorect;
    }

    public Integer getNumberQuestionParalysis() {
        return numberQuestionParalysis;
    }

    public void setNumberQuestionParalysis(Integer numberQuestionParalysis) {
        this.numberQuestionParalysis = numberQuestionParalysis;
    }

}
