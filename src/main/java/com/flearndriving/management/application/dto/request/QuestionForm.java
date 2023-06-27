package com.flearndriving.management.application.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class QuestionForm {

    private Integer number;

    private String content;

    private Boolean isParalysis;

    private List<AnswerForm> listAnswers;

    private MultipartFile[] images;

    private Long chapterId;

}
