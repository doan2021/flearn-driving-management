package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Answer extends AbstractEntity {

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "boolean default false")
    private Boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
}
