/**
 * 
 */
package com.flearndriving.management.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "exam_profile")
public class ExamProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_profile_id")
    private Long examProfileId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthDay;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone", length = 10)
    private String numberPhone;

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "working_unit")
    private String workingUnit;

    @Column(name = "identity_card_number")
    private String identityCardNumber;

    @Column(name = "status")
    private Integer status;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "point")
    private double point;

    @Column(name = "is_pass")
    private boolean isPass;

    @Column(name = "time_exam_start")
    private String timeExamStart;

    @Column(name = "time_exam_end")
    private String timeExamEnd;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "place_of_issue")
    private Province placeOfIssue;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @JsonManagedReference
    @OneToMany(mappedBy = "examProfile", cascade = CascadeType.ALL)
    private List<Document> listDocuments;

    @JsonManagedReference
    @OneToMany(mappedBy = "examProfile", cascade = CascadeType.ALL)
    private List<HistoryAnswer> listHistoryAnswer;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getWorkingUnit() {
        return workingUnit;
    }

    public void setWorkingUnit(String workingUnit) {
        this.workingUnit = workingUnit;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Province getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(Province placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public List<Document> getListDocuments() {
        return listDocuments;
    }

    public void setListDocuments(List<Document> listDocuments) {
        this.listDocuments = listDocuments;
    }

    public Long getExamProfileId() {
        return examProfileId;
    }

    public void setExamProfileId(Long examProfileId) {
        this.examProfileId = examProfileId;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean isPass) {
        this.isPass = isPass;
    }

    public String getTimeExamStart() {
        return timeExamStart;
    }

    public void setTimeExamStart(String timeExamStart) {
        this.timeExamStart = timeExamStart;
    }

    public String getTimeExamEnd() {
        return timeExamEnd;
    }

    public void setTimeExamEnd(String timeExamEnd) {
        this.timeExamEnd = timeExamEnd;
    }

    public List<HistoryAnswer> getListHistoryAnswer() {
        return listHistoryAnswer;
    }

    public void setListHistoryAnswer(List<HistoryAnswer> listHistoryAnswer) {
        this.listHistoryAnswer = listHistoryAnswer;
    }

}
