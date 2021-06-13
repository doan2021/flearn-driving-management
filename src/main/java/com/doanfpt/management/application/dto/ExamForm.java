package com.doanfpt.management.application.dto;

public class ExamForm {
	private String name;
	private String description;
	private String dateRegisExamStart;
	private String dateRegisExamEnd;

	public String getName() {
		return name;
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
