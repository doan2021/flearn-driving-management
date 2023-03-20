package com.flearndriving.management.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Customer extends AbstractEntity {

    @Column(length = 36)
    private String userName;

    @Column(length = 128, nullable = false)
    private String encrytedPassword;

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

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "ward_id", referencedColumnName = "id")
    private Ward ward;

    @Column(columnDefinition = "Boolean default false")
    private Boolean isDelete;

    @Column(length = 15)
    private String authProvider;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> listImages;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamProfile> listExamProfile;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TrialExamResult> listTrialExamResult;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StatusLearn> listStatusLearn;
}
