package com.flearndriving.management.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormSearchExamQuestions {

	private String name;

	private String description;

	private String updateAtFrom;

	private String updateAtTo;

	private Integer pageNumber;

}
