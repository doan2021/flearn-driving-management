package com.doanfpt.management.application.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "exam")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_id")
	private Long examId;

	@Column(name = "name")
	private String name;

	@Column(name = "is_trial")
	public boolean isTrial;

	@Column(name = "date_regis_exam_start")
	private Date dateRegisExamStart;

	@Column(name = "date_regis_exam_end")
	private Date dateRegisExamEnd;

	@Column(name = "date_exam")
	private Date dateExam;

	@Column(name = "description")
	private String description;

	@Column(name = "is_delete", columnDefinition = "boolean default false")
	private boolean isDelete;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_at")
	private Date createAt;

	@Column(name = "update_by")
	private String updateBy;

	@Column(name = "update_at")
	private Date updateAt;

	@JsonManagedReference
	@OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
	private List<ExamResult> listExamResult;

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTrial() {
		return isTrial;
	}

	public void setTrial(boolean isTrial) {
		this.isTrial = isTrial;
	}

	public Date getDateRegisExamStart() {
		return dateRegisExamStart;
	}

	public void setDateRegisExamStart(Date date) {
		this.dateRegisExamStart = date;
	}

	public Date getDateRegisExamEnd() {
		return dateRegisExamEnd;
	}

	public void setDateRegisExamEnd(Date dateRegisExamEnd) {
		this.dateRegisExamEnd = dateRegisExamEnd;
	}

	public Date getDateExam() {
		return dateExam;
	}

	public void setDateExam(Date dateExam) {
		this.dateExam = dateExam;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
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

	public List<ExamResult> getListExamResult() {
		return listExamResult;
	}

	public void setListExamResult(List<ExamResult> listExamResult) {
		this.listExamResult = listExamResult;
	}

}
