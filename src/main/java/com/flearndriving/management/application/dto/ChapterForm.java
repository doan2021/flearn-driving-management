package com.flearndriving.management.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ChapterForm {

    private Long chapterId;

    private String name;

    private String content;

    private String description;

    private String createBy;

    private String createAt;

    private String updateBy;

    private String updateAt;

    private MultipartFile[] images;

}
