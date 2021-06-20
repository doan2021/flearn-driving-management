package com.doanfpt.management.application.dto;

public class ExamForm {
	private Long examId;
	private String name;
	private String description;
	private String dateRegisExamStart;
	private String dateRegisExamEnd;
	private String dateExam;
	private String updateBy;
	private String updateAt;

	public String getName() {
		return name;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public String getDateExam() {
		return dateExam;
	}

	public void setDateExam(String dateExam) {
		this.dateExam = dateExam;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateRegisExamStart() {
		return dateRegisExamStart;
	}

	public void setDateRegisExamStart(String dateRegisExamStart) {
		this.dateRegisExamStart = dateRegisExamStart;
	}

	public String getDateRegisExamEnd() {
		return dateRegisExamEnd;
	}

	public void setDateRegisExamEnd(String dateRegisExamEnd) {
		this.dateRegisExamEnd = dateRegisExamEnd;
	}
}
