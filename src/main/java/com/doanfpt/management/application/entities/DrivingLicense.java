package com.doanfpt.management.application.entities;

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
@Table(name = "driving_license")
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driving_license_id")
    private Long drivingLicenseId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "number_question")
    private Integer numberQuestion;

    @Column(name = "number_question_paralysis")
    private Integer numberQuestionParalysis;

    @Column(name = "number_question_correct")
    private Integer numberQuestionCorect;

    @Column(name = "exam_minutes")
    private Integer examMinutes;

    @Column(name = "description")
    private String description;

    @Column(name = "number_year_expires")
    private Integer numberYearExpires;

    @JsonManagedReference
    @OneToMany(mappedBy = "drivingLicense", cascade = CascadeType.ALL)
    private List<ExamStructure> listExamStructure;

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

    public List<ExamStructure> getListExamStructure() {
        return listExamStructure;
    }

    public void setListExamStructure(List<ExamStructure> listExamStructure) {
        this.listExamStructure = listExamStructure;
    }

    public Integer getNumberQuestionParalysis() {
        return numberQuestionParalysis;
    }

    public void setNumberQuestionParalysis(Integer numberQuestionParalysis) {
        this.numberQuestionParalysis = numberQuestionParalysis;
    }

    public Integer getNumberQuestionCorect() {
        return numberQuestionCorect;
    }

    public void setNumberQuestionCorect(Integer numberQuestionCorect) {
        this.numberQuestionCorect = numberQuestionCorect;
    }

}
