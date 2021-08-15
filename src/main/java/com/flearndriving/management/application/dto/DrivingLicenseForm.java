package com.flearndriving.management.application.dto;

import java.util.List;

public class DrivingLicenseForm {
	private Long drivingLicenseId;
	private String name;
	private String price;
	private String numberQuestion;
	private String numberQuestionCorect;
	private String numberQuestionParalysis;
	private String description;
	private String examMinutes;
	private String numberYearExpires;
	private List<NumberOfChapter> listNumberOfChapter;

	public Long getDrivingLicenseId() {
		return drivingLicenseId;
	}

	public void setDrivingLicenseId(Long drivingLicenseId) {
		this.drivingLicenseId = drivingLicenseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumberQuestion() {
		return numberQuestion;
	}

	public void setNumberQuestion(String numberQuestion) {
		this.numberQuestion = numberQuestion;
	}

	public String getNumberQuestionCorect() {
		return numberQuestionCorect;
	}

	public void setNumberQuestionCorect(String numberQuestionCorect) {
		this.numberQuestionCorect = numberQuestionCorect;
	}

	public String getNumberQuestionParalysis() {
		return numberQuestionParalysis;
	}

	public void setNumberQuestionParalysis(String numberQuestionParalysis) {
		this.numberQuestionParalysis = numberQuestionParalysis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExamMinutes() {
		return examMinutes;
	}

	public void setExamMinutes(String examMinutes) {
		this.examMinutes = examMinutes;
	}

	public String getNumberYearExpires() {
		return numberYearExpires;
	}

	public void setNumberYearExpires(String numberYearExpires) {
		this.numberYearExpires = numberYearExpires;
	}

	public List<NumberOfChapter> getListNumberOfChapter() {
		return listNumberOfChapter;
	}

	public void setListNumberOfChapter(List<NumberOfChapter> listNumberOfChapter) {
		this.listNumberOfChapter = listNumberOfChapter;
	}

}
