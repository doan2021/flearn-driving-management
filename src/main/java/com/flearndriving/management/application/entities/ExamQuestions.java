package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ExamQuestions extends AbstractEntity {

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Integer status;

    @OneToMany(mappedBy = "examQuestions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamQuestionsDetail> listExamQuestionsDetail;

    @Column(columnDefinition = "Boolean default false")
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "driving_license_id", referencedColumnName = "id")
    private DrivingLicense drivingLicense;
}
