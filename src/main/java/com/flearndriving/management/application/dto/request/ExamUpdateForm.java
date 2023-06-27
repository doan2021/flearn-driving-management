package com.flearndriving.management.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExamUpdateForm {

    private Long examId;

    private String name;

    private String description;

    private String strDateRegisExamEnd;

    private String strDateExam;

    private Integer status;

    private String typeDrivingLicense;

    private Date dateRegisExamEnd;

    private Date dateExam;

}
