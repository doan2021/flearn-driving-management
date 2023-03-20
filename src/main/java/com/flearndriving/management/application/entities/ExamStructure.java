package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ExamStructure extends AbstractEntity {

    @Column
    private Integer numberQuestion;

    @ManyToOne
    @JoinColumn(name = "driving_license_id", referencedColumnName = "id")
    private DrivingLicense drivingLicense;

    @ManyToOne
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private Chapter chapter;
}
