package com.flearndriving.management.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamQuestionsForm {

    private String name;

    private String description;

    private Long drivingLicenseId;

    private List<Long> listIdQuestion;

    private List<Long> listIdQuestionParalysis;

}
