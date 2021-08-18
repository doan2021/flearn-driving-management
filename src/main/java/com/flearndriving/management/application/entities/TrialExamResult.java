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
@Table(name = "trial_exam_result")
public class TrialExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trial_exam_result_id")
	private Long trialExamResultId;

	@Column(name = "point")
	private double point;

	@Column(name = "is_pass")
	private boolean isPass;

	@Column(name = "time_exam_start")
	private Integer timeExam;

	@JsonManagedReference
	@OneToMany(mappedBy = "trialExamResult", cascade = CascadeType.ALL)
	private List<HistoryAnswer> listHistoryAnswer;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "exam_questions_id")
	private ExamQuestions examQuestions;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_at")
	private Date updateAt;

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

	public List<HistoryAnswer> getListHistoryAnswer() {
		return listHistoryAnswer;
	}

	public void setListHistoryAnswer(List<HistoryAnswer> listHistoryAnswer) {
		this.listHistoryAnswer = listHistoryAnswer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ExamQuestions getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(ExamQuestions examQuestions) {
		this.examQuestions = examQuestions;
	}

	public Long getTrialExamResultId() {
		return trialExamResultId;
	}

	public void setTrialExamResultId(Long trialExamResultId) {
		this.trialExamResultId = trialExamResultId;
	}

	public Integer getTimeExam() {
		return timeExam;
	}

	public void setTimeExam(Integer timeExam) {
		this.timeExam = timeExam;
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

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

}
