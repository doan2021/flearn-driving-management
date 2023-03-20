package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Document extends AbstractEntity {

    @Column
    private String fileName;

    @Column
    private String originFileName;

    @Column
    private Long size;

    @Column
    private String extension;

    @Column
    private String contentType;

    @Column
    private String path;

    @Column
    private Integer type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "exam_profile_id", referencedColumnName = "id")
    private ExamProfile examProfile;

    @ManyToOne
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private Chapter chapter;
}
