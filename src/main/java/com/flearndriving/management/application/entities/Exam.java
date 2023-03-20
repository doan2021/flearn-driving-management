package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Exam extends AbstractEntity {

    @Column
    private String name;

    @Column
    private Date dateRegisExamEnd;

    @Column
    private Date dateExam;

    @Column
    private Integer status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "driving_license_id", referencedColumnName = "id")
    private DrivingLicense drivingLicense;
}
