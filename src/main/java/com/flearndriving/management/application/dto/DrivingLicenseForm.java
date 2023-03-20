package com.flearndriving.management.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrivingLicenseForm {

	private Long drivingLicenseId;

	private String name;

	private String price;

	private String numberQuestion;

	private String numberQuestionCorrect;

	private String numberQuestionParalysis;

	private String description;

	private String examMinutes;

	private String numberYearExpires;

	private List<NumberOfChapter> listNumberOfChapter;

}
