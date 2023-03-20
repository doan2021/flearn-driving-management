package com.flearndriving.management.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormSearchDrivingLicense {

	private String name;

	private String price;

	private String numberQuestion;

	private String numberQuestionParalysis;

	private String examMinutes;

	private String numberYearExpires;

	private Integer pageNumber;

}
