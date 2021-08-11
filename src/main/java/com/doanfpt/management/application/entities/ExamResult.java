package com.doanfpt.management.application.entities;

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
@Table(name = "exam_result")
public class ExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_result_id")
	private Long examResultId;

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
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@JsonManagedReference
	@OneToMany(mappedBy = "examResult", cascade = CascadeType.ALL)
	private List<HistoryAnswer> listHistoryAnswer;

	public Long getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(Long examResultId) {
		this.examResultId = examResultId;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public List<HistoryAnswer> getListHistoryAnswer() {
		return listHistoryAnswer;
	}

	public void setListHistoryAnswer(List<HistoryAnswer> listHistoryAnswer) {
		this.listHistoryAnswer = listHistoryAnswer;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
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

}
