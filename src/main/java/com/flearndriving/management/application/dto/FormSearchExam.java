package com.flearndriving.management.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
