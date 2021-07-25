package com.doanfpt.management.application.dto;

public class FormSearchExamQuestions {
	private String name;
	private String description;
	private String updateAtFrom;
	private String updateAtTo;
	private Integer pageNumber;

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

	public String getUpdateAtFrom() {
		return updateAtFrom;
	}

	public void setUpdateAtFrom(String updateAtFrom) {
		this.updateAtFrom = updateAtFrom;
	}

	public String getUpdateAtTo() {
		return updateAtTo;
	}

	public void setUpdateAtTo(String updateAtTo) {
		this.updateAtTo = updateAtTo;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

}
