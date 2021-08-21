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
@Table(name = "status_learn")
public class StatusLearn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_learn_id")
    private Long statusLearnId;

    @Column(name = "correct_number_of_times")
    private Integer correctNumberOfTimes;

    @Column(name = "incorrect_number_of_times")
    private Integer incorrectNumberOfTimes;

    @Column(name = "status_question")
    private int statusQuestion;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_at")
    private Date updateAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonManagedReference
    @OneToMany(mappedBy = "statusLearn", cascade = CascadeType.ALL)
    private List<HistoryAnswer> listHistoryAnswer;

    public Long getStatusLearnId() {
        return statusLearnId;
    }

    public void setStatusLearnId(Long statusLearnId) {
        this.statusLearnId = statusLearnId;
    }

    public Integer getCorrectNumberOfTimes() {
        return correctNumberOfTimes;
    }

    public void setCorrectNumberOfTimes(Integer correctNumberOfTimes) {
        this.correctNumberOfTimes = correctNumberOfTimes;
    }

    public Integer getIncorrectNumberOfTimes() {
        return incorrectNumberOfTimes;
    }

    public void setIncorrectNumberOfTimes(Integer incorrectNumberOfTimes) {
        this.incorrectNumberOfTimes = incorrectNumberOfTimes;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getStatusQuestion() {
        return statusQuestion;
    }

    public void setStatusQuestion(int statusQuestion) {
        this.statusQuestion = statusQuestion;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public List<HistoryAnswer> getListHistoryAnswer() {
        return listHistoryAnswer;
    }

    public void setListHistoryAnswer(List<HistoryAnswer> listHistoryAnswer) {
        this.listHistoryAnswer = listHistoryAnswer;
    }

}
