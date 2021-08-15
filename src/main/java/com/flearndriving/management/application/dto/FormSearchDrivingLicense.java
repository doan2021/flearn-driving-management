package com.flearndriving.management.application.dto;

public class FormSearchDrivingLicense {
	private String name;
	private String price;
	private String numberQuestion;
	private String numberQuestionParalysis;
	private String examMinutes;
	private String numberYearExpires;
	private Integer pageNumber;

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

	public String getNumberQuestionParalysis() {
		return numberQuestionParalysis;
	}

	public void setNumberQuestionParalysis(String numberQuestionParalysis) {
		this.numberQuestionParalysis = numberQuestionParalysis;
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

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

}
