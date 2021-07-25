package com.doanfpt.management.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driving_license")
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driving_license_id")
    private Long drivingLicenseId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price;

    @Column(name = "number_question")
    private Integer numberQuestion;

    @Column(name = "number_question_correct")
    private Integer numberQuestionCorrect;

    @Column(name = "exam_minutes")
    private Integer examMinutes;

    @Column(name = "description")
    private String description;

    @Column(name = "number_year_expires")
    private Integer numberYearExpires;

    @Column(name = "number_question_in_chapter", columnDefinition="TEXT")
    private String numberQuestionInChapter;

    public Long getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(Long drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberYearExpires() {
        return numberYearExpires;
    }

    public void setNumberYearExpires(Integer numberYearExpires) {
        this.numberYearExpires = numberYearExpires;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(Integer numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public Integer getNumberQuestionCorrect() {
        return numberQuestionCorrect;
    }

    public void setNumberQuestionCorrect(Integer numberQuestionCorrect) {
        this.numberQuestionCorrect = numberQuestionCorrect;
    }

    public String getNumberQuestionInChapter() {
        return numberQuestionInChapter;
    }

    public void setNumberQuestionInChapter(String numberQuestionInChapter) {
        this.numberQuestionInChapter = numberQuestionInChapter;
    }

}
