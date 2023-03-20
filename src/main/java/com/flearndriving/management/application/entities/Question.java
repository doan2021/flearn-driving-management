package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Question extends AbstractEntity {

    @Column
    private Integer number;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "Boolean default false")
    private Boolean isParalysis;

    @Column(columnDefinition = "Boolean default false")
    private Boolean isDelete;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> listImage;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> listAnswers;

    @ManyToOne
    @JoinColumn(name = "chapter_id", referencedColumnName = "id")
    private Chapter chapter;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamQuestionsDetail> listExamQuestionsDetail;
}
