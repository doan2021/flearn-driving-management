package com.flearndriving.management.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamForm {

    private Long examId;

    private String name;

    private String description;

    private String dateRegisExamEnd;

    private String dateExam;

    private String createBy;

    private String createAt;

    private String updateBy;

    private String updateAt;

    private Long drivingLicenseId;

}
