package com.flearndriving.management.application.dto;

public class FormSearchExam {
	private String name;
	private String description;
	private String dateRegisExamStartFrom;
	private String dateRegisExamStartTo;
	private String dateRegisExamEndFrom;
	private String dateRegisExamEndTo;
	private String updateAtFrom;
	private String updateAtTo;
	private String updateBy;

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

	public String getDateRegisExamStartFrom() {
		return dateRegisExamStartFrom;
	}

	public void setDateRegisExamStartFrom(String dateRegisExamStartFrom) {
		this.dateRegisExamStartFrom = dateRegisExamStartFrom;
	}

	public String getDateRegisExamStartTo() {
		return dateRegisExamStartTo;
	}

	public void setDateRegisExamStartTo(String dateRegisExamStartTo) {
		this.dateRegisExamStartTo = dateRegisExamStartTo;
	}

	public String getDateRegisExamEndFrom() {
		return dateRegisExamEndFrom;
	}

	public void setDateRegisExamEndFrom(String dateRegisExamEndFrom) {
		this.dateRegisExamEndFrom = dateRegisExamEndFrom;
	}

	public String getDateRegisExamEndTo() {
		return dateRegisExamEndTo;
	}

	public void setDateRegisExamEndTo(String dateRegisExamEndTo) {
		this.dateRegisExamEndTo = dateRegisExamEndTo;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

}
