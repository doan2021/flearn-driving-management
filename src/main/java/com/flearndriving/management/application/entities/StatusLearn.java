package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class StatusLearn extends AbstractEntity {

    @Column
    private Integer correctNumberOfTimes;

    @Column
    private Integer incorrectNumberOfTimes;

    @Column
    private int statusQuestion;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "statusLearn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoryAnswer> listHistoryAnswer;
}
