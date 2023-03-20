package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ExamProfile extends AbstractEntity {

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    private Date birthDay;

    @Column
    private Integer gender;

    @Column
    private String email;

    @Column(length = 10)
    private String numberPhone;

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String workingUnit;

    @Column
    private String identityCardNumber;

    @Column
    private Integer status;

    @Column
    private Date issueDate;

    @Column
    private Double point;

    @Column
    private Double isPass;

    @Column
    private String timeExamStart;

    @Column
    private String timeExamEnd;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "place_of_issue_id", referencedColumnName = "id")
    private Province placeOfIssue;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;

    @OneToMany(mappedBy = "examProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> listDocuments;

    @OneToMany(mappedBy = "examProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistoryAnswer> listHistoryAnswer;
}
