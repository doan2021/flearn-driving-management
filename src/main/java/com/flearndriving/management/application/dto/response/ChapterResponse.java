package com.flearndriving.management.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@Builder
public class ChapterResponse {

    private Long chapterId;

    private String name;

    private String content;

    private String description;

    private MultipartFile[] images;

    private String lastModifiedBy;

    private Date lastModifiedDate;
}
