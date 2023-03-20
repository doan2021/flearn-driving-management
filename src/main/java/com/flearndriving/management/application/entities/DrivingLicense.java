package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class DrivingLicense extends AbstractEntity {

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Integer numberQuestion;

    @Column
    private Integer numberQuestionParalysis;

    @Column
    private Integer numberQuestionCorrect;

    @Column
    private Integer examMinutes;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Integer numberYearExpires;

    @Column(columnDefinition = "Boolean default false")
    private Boolean isDelete;

    @OneToMany(mappedBy = "drivingLicense", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamStructure> listExamStructure;

}
