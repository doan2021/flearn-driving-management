package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Chapter extends AbstractEntity {

    @Column
    private String name;

    @Column
    private String content;

    @Column(columnDefinition = "Boolean default false")
    private Boolean isDelete;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> listQuestion;

    @OneToMany(mappedBy = "chapter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> listImages;
}
